package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class WatchlistDto {

    @Schema(example = "1", required = true)
    private int id;

    @Schema(example = "Моё избранное", required = true)
    @Size(max = 36)
    private String name;

    @Schema(example = "Эксперементальные идеи с reddit", required = false)
    @Size(max = 160)
    private String comments;

}
