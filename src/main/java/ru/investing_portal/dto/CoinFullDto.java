package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.joda.time.DateTime;

import javax.validation.constraints.PastOrPresent;

@Data
public class CoinFullDto {

    @Schema(example = "1")
    private int id;

    @Schema(example = "bitcoin")
    private String api_id;

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

    @Schema(example = "2")
    private int marketCapRank;

    @Schema(example = "431767465324")
    private double fullyDilutedValuation;

    @Schema(example = "45613720399")
    private double totalVolume;

    @Schema(example = "20955")
    private double high24h;

    @Schema(example = "20437")
    private double low24h;

    @Schema(example = "-30.0896")
    private double priceChange24h;

    @Schema(example = "-660746048.2291")
    private double marketCapChange24h;

    @Schema(example = "-0.16718")
    private double marketCapChangePercentage24h;

    @Schema(example = "19190731")
    private double circulatingSupply;

    @Schema(example = "21000000")
    private double totalSupply;

    @Schema(example = "21000000")
    private double maxSupply;

    @Schema(example = "69045")
    private double ath;

    @Schema(example = "-70.20804")
    private double athChangePercentage;

    @Schema(example = "2021-11-10 14:24:11.849")
    @PastOrPresent
    private DateTime athDate;

    @Schema(example = "67.81")
    private double atl;

    @Schema(example = "30234.90149")
    private double atlChangePercentage;

    @Schema(example = "2013-07-06 00:00:00")
    @PastOrPresent
    private DateTime atlDate;

    @Schema(example = "0.034399")
    private double changePercentage1h;

    @Schema(example = "-0.146127")
    private double changePercentage24h;

    @Schema(example = "7.458260")
    private double changePercentage7d;

    @Schema(example = "2022-10-27 12:04:18.925")
    private DateTime lastUpdated;

}
