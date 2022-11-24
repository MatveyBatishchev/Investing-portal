package ru.investing_portal.config.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ExceptionHandlerFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("context-path", request.getContextPath());
            responseBody.put("timestamp", DateTime.now().toString());
            responseBody.put("error", exception.getClass().getSimpleName());
            responseBody.put("reason", exception.getMessage());

            response.setStatus(401);
            response.setContentType(APPLICATION_JSON_VALUE);

            new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
        }
    }

}
