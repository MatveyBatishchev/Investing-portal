package ru.investing_portal.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        buildResponse(authException, request.getContextPath(), response);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        buildResponse(accessDeniedException, request.getContextPath(), response);
    }

    private void buildResponse(Exception exception, String contextPath, HttpServletResponse response) throws IOException {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("context-path", contextPath);
        responseBody.put("timestamp", DateTime.now().toString());
        responseBody.put("error", exception.getClass().getSimpleName());
        responseBody.put("reason", exception.getMessage());

        response.setStatus(401);
        response.setContentType(APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }

}

