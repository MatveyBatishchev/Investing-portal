package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class WatchlistReadDto {

    @Schema(example = "1", required = true)
    private int id;

    @Schema(example = "Моё избранное", required = true)
    @Size(max = 36)
    private String name;

    @Schema(example = "Эксперементальные идеи с reddit", required = false)
    @Size()
    private String comments;

    @Schema(required = false)
    private List<CoinShortDto> coins;

}
