package ru.investing_portal.config.util;

import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CookieUtil {

    private static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
    private static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";

    @Value("${jwt.expiration-time.access-token}")
    private int jwtAccessTokenExpirationMs;

    @Value("${jwt.expiration-time.refresh-token}")
    private int jwtRefreshTokenExpirationMs;

    public HttpCookie createAccessTokenCookie(String token) {
        return ResponseCookie.from(ACCESS_TOKEN_COOKIE_NAME, token)
                .maxAge(jwtAccessTokenExpirationMs)
                .httpOnly(true)
                .path("/")
                .sameSite(SameSiteCookies.UNSET.getValue())
                .build();
    }

    public HttpCookie createRefreshTokenCookie(String token) {
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, token)
                .maxAge(jwtRefreshTokenExpirationMs)
                .httpOnly(true)
                .path("/")
                .sameSite(SameSiteCookies.UNSET.getValue())
                .build();
    }

    public List<HttpCookie> deleteTokenCookies() {
        ResponseCookie accessTokenCookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE_NAME, "").maxAge(0).httpOnly(true).path("/").build();
        ResponseCookie refreshTokenCookie = ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, "").maxAge(0).httpOnly(true).path("/").build();
        return List.of(accessTokenCookie, refreshTokenCookie);
    }
}


