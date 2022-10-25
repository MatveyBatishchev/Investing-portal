package ru.investing_portal.dto;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class CoinFullDto {

    private int id;

    private String api_id;

    private String symbol;

    private String name;

    private String image;

    private double currentPrice;

    private double marketCap;

    private int marketCapRank;

    private double fullyDilutedValuation;

    private double totalVolume;

    private double high24h;

    private double low24h;

    private double priceChange24h;

    private double marketCapChange24h;

    private double marketCapChangePercentage24h;

    private double circulatingSupply;

    private double totalSupply;

    private double maxSupply;

    private double ath;

    private double athChangePercentage;

    private DateTime athDate;

    private double atl;

    private double atlChangePercentage;

    private DateTime atlDate;

    private double changePercentage1h;

    private double changePercentage24h;

    private double changePercentage7d;

    private DateTime lastUpdated;

}
