package ru.investing_portal.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.investing_portal.dto.user.JwtRequest;
import ru.investing_portal.dto.user.JwtResponse;

import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(produces = APPLICATION_JSON_VALUE)
@Tag(name = "Auth")
public interface AuthController {

    @PostMapping(path = "/login", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Operation(summary = "Аутентификация в системе")
    @ResponseStatus(HttpStatus.OK)
    JwtResponse login(@Valid JwtRequest jwtRequest);


    @PostMapping("/logout")
    @Operation(summary = "Выходы из системы")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void logout();

    @PostMapping("/token/refresh")
    @Operation(summary = "Возвращает обновлённый accessToken и прежний refreshToken")
    @ResponseStatus(HttpStatus.OK)
    JwtResponse refresh(@RequestHeader(name = AUTHORIZATION, required = true) String bearerToken);

}
