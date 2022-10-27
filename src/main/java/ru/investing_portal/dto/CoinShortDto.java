package ru.investing_portal.dto;

import lombok.Data;

@Data
public class CoinShortDto {

    private int id;

    private String api_id;

    private String symbol;

    private String name;

    private String image;

    private double currentPrice;

    private double marketCap;

    private double fullyDilutedValuation;

    private double changePercentage1h;

    private double changePercentage24h;

    private double changePercentage7d;

}
