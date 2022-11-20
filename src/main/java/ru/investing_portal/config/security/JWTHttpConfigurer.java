package ru.investing_portal.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import ru.investing_portal.config.security.filters.CustomJWTAuthenticationFilter;

public class JWTHttpConfigurer extends AbstractHttpConfigurer<JWTHttpConfigurer, HttpSecurity> {

    public static JWTHttpConfigurer jwtHttpConfigurer() {
        return new JWTHttpConfigurer();
    }

    @Override
    public void configure(HttpSecurity http) {
        final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new CustomJWTAuthenticationFilter(authenticationManager));
    }

}
