package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CoinShortDto {

    @Schema(example = "1")
    private int id;

    @Schema(example = "bitcoin")
    private String apiId;

    @Schema(example = "BTC")
    private String symbol;

    @Schema(example = "Bitcoin")
    private String name;

    @Schema(example = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579")
    private String image;

    @Schema(example = "20561")
    private double currentPrice;

    @Schema(example = "394568251504")
    private double marketCap;

    @Schema(example = "431767465324")
    private double fullyDilutedValuation;

    @Schema(example = "0.034399")
    private double changePercentage1h;

    @Schema(example = "-0.146127")
    private double changePercentage24h;

    @Schema(example = "7.458260")
    private double changePercentage7d;

}
