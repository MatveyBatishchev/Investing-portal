package ru.investing_portal.services.user;

import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.investing_portal.dto.user.JwtRequest;
import ru.investing_portal.dto.user.JwtResponse;
import ru.investing_portal.util.CookieUtil;
import ru.investing_portal.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    private final CookieUtil cookieUtil;

    public ResponseEntity<JwtResponse> loginUser(JwtRequest jwtRequest) {
        try {
            String email = jwtRequest.getUsername();
            String password = jwtRequest.getPassword();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();


            JwtResponse response = jwtTokenUtil.generateTokens(user);

            HttpHeaders responseHeaders = new HttpHeaders(); // создаём cookie под токены
            responseHeaders.add(SET_COOKIE, cookieUtil.createAccessTokenCookie(response.getAccessToken()).toString());
            responseHeaders.add(SET_COOKIE, cookieUtil.createRefreshTokenCookie(response.getRefreshToken()).toString());

            return ResponseEntity.ok().headers(responseHeaders).body(response);

        } catch(BadCredentialsException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
    }

    public JwtResponse refreshUserToken(String bearerToken) {
        return jwtTokenUtil.refreshToken(bearerToken);
    }

    public ResponseEntity<Map<String, Object>> logout() {
        cookieUtil.deleteTokenCookies(); // удаляем cookie связанные с токенами

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(SET_COOKIE, cookieUtil.createRefreshTokenCookie("invalidated").toString());
        responseHeaders.add(SET_COOKIE, cookieUtil.createAccessTokenCookie("invalidated").toString());

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "Logout successful.");
        responseBody.put("time", DateTime.now());

        return ResponseEntity.ok().headers(responseHeaders).body(responseBody);
    }

}
