package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.investing_portal.models.domain.WebResourceType;

import javax.validation.constraints.Size;

@Data
public class WebResourceDto {

    @Schema(example = "1", required = true)
    private int id;

    @Schema(example = "1", required = true)
    private int coinId;

    @Schema(example = "Telegram", required = true)
    @Size(max = 32)
    private String name;

    @Schema(example = "https://web.telegram.org/k/", required = true)
    @Size(max = 255)
    private String url;

    @Schema(example = "SOCIAL_NETWORK", required = true)
    private WebResourceType webResourceType;

}


