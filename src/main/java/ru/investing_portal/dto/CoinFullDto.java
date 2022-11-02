package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.joda.time.DateTime;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Data
public class CoinFullDto {

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

    @Schema(example = "2", required = true)
    private int marketCapRank;

    @Schema(example = "431767465324", required = true)
    private double fullyDilutedValuation;

    @Schema(example = "45613720399", required = true)
    private double totalVolume;

    @Schema(example = "20955", required = true)
    private double high24h;

    @Schema(example = "20437", required = true)
    private double low24h;

    @Schema(example = "-30.0896", required = true)
    private double priceChange24h;

    @Schema(example = "-660746048.2291", required = true)
    private double marketCapChange24h;

    @Schema(example = "-0.16718", required = true)
    private double marketCapChangePercentage24h;

    @Schema(example = "19190731", required = true)
    private double circulatingSupply;

    @Schema(example = "21000000", required = true)
    private double totalSupply;

    @Schema(example = "21000000", required = true)
    private double maxSupply;

    @Schema(example = "69045", required = true)
    private double ath;

    @Schema(example = "-70.20804", required = true)
    private double athChangePercentage;

    @Schema(example = "2021-11-10 14:24:11.849", required = true)
    @PastOrPresent
    private DateTime athDate;

    @Schema(example = "67.81", required = true)
    private double atl;

    @Schema(example = "30234.90149", required = true)
    private double atlChangePercentage;

    @Schema(example = "2013-07-06 00:00:00", required = true)
    @PastOrPresent
    private DateTime atlDate;

    @Schema(example = "0.034399", required = true)
    private double changePercentage1h;

    @Schema(example = "-0.146127", required = true)
    private double changePercentage24h;

    @Schema(example = "7.458260", required = true)
    private double changePercentage7d;

    @Schema(example = "2022-10-27 12:04:18.925", required = true)
    private DateTime lastUpdated;

}
