package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryDto {

    @Schema(example = "1")
    private int id;

    @Schema(example = "Avalanche Ecosystem")
    private String name;

}
