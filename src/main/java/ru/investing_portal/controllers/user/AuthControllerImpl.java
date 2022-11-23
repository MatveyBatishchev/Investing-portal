package ru.investing_portal.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.user.JwtRequest;
import ru.investing_portal.dto.user.JwtResponse;
import ru.investing_portal.services.user.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public JwtResponse login(JwtRequest jwtRequest) {
        return authService.loginUser(jwtRequest);
    }

    @Override
    public void logout() {

    }

    @Override
    public JwtResponse refresh(String bearerToken) {
        return authService.refreshUserToken(bearerToken);
    }
}
