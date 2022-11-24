package ru.investing_portal.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JwtRequest {

    @NotBlank
    private String username; // email

    @NotBlank
    private String password;

}
