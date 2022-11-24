package ru.investing_portal.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.user.JwtRequest;
import ru.investing_portal.dto.user.JwtResponse;
import ru.investing_portal.services.user.AuthService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ResponseEntity<JwtResponse> login(JwtRequest jwtRequest) {
        return authService.loginUser(jwtRequest);
    }

    @Override
    public JwtResponse refresh(String bearerToken) {
        return authService.refreshUserToken(bearerToken);
    }

    @Override
    public ResponseEntity<Map<String, Object>> logout() {
        return authService.logout();
    }
}
