package ru.investing_portal.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.investing_portal.dto.user.JwtResponse;
import ru.investing_portal.services.user.UserDetailServiceImpl;
import ru.investing_portal.services.user.UserDetailsImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    @Value("${jwt.expiration-time.access-token}")
    private int jwtAccessTokenExpirationMs;

    @Value("${jwt.expiration-time.refresh-token}")
    private int jwtRefreshTokenExpirationMs;

    private final UserDetailServiceImpl userDetailService;

    private final Algorithm algorithm;

    private final Date issuedAt = DateTime.now().toDate();

    @Autowired
    public JwtTokenUtil(UserDetailServiceImpl userDetailService, @Value("${jwt.hmac256.secret-key}") String secretKey) {
        this.userDetailService = userDetailService;
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    public JwtResponse generateTokens(UserDetailsImpl user) {

        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String accessToken = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + jwtAccessTokenExpirationMs))
            .withIssuedAt(issuedAt)
            .withClaim("roles", roles)
            .sign(algorithm);

        Date refreshTokenExpirationDate = new Date(System.currentTimeMillis() + jwtRefreshTokenExpirationMs);
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(refreshTokenExpirationDate)
                .withIssuedAt(issuedAt)
                .sign(algorithm);

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse refreshToken(String bearerToken) {
        String refreshToken = bearerToken.substring("Bearer ".length());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);

        String email = decodedJWT.getSubject();
        UserDetails user = userDetailService.loadUserByUsername(email);
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtAccessTokenExpirationMs))
                .withIssuedAt(issuedAt)
                .withClaim("roles", roles)
                .sign(algorithm);

        return new JwtResponse(accessToken, refreshToken);
        // CHECKME exception handling?
    }

}
