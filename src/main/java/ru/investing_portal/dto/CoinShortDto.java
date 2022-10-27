package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CoinShortDto {

    @Schema(example = "1", required = true)
    private int id;

    @Schema(example = "bitcoin", required = true)
    @Size(max = 50)
    private String api_id;

    @Schema(example = "BTC", required = true)
    @Size(max = 8)
    private String symbol;

    @Schema(example = "Bitcoin", required = true)
    @Size(max = 32)
    private String name;

    @Schema(example = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579", required = true)
    @Size(max = 255)
    private String image;

    @Schema(example = "20561", required = true)
    private double currentPrice;

    @Schema(example = "394568251504", required = true)
    private double marketCap;

    @Schema(example = "431767465324", required = true)
    private double fullyDilutedValuation;

    @Schema(example = "0.034399", required = true)
    private double changePercentage1h;

    @Schema(example = "-0.146127", required = true)
    private double changePercentage24h;

    @Schema(example = "7.458260", required = true)
    private double changePercentage7d;

}
