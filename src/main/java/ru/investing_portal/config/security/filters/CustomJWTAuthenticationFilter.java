package ru.investing_portal.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("Hi");
        response.setStatus(401);
        response.setContentType(APPLICATION_JSON_VALUE);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("context-path", request.getContextPath());
        responseBody.put("timestamp", DateTime.now().toString());
        responseBody.put("error", failed.getMessage());

        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }

}
