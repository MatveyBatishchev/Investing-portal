package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDto {

    @Schema(example = "1")
    private int id;

    @Schema(example = "Matvey Batishchev")
    private String username;

    @Schema(example = "batishev@gmail.com")
    private String email;

    @Schema(example = "Bitcoin2077%")
    private String password;

}
