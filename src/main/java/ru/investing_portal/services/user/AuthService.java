package ru.investing_portal.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.investing_portal.config.security.JwtTokenUtil;
import ru.investing_portal.dto.user.JwtRequest;
import ru.investing_portal.dto.user.JwtResponse;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    public JwtResponse loginUser(JwtRequest jwtRequest) {
        try {
            String email = jwtRequest.getUsername();
            String password = jwtRequest.getPassword();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

            return jwtTokenUtil.generateTokens(user);
        } catch(BadCredentialsException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
    }

    public JwtResponse refreshUserToken(String bearerToken) {
        return jwtTokenUtil.refreshToken(bearerToken);
    }





}
