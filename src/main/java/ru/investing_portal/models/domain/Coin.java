package ru.investing_portal.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin {

    /**
     * Идентификатор монеты
     */
    @JsonIgnore
    private int id;

    /**
     * Идентификатор монеты в api CoinGeko
     */
    @JsonProperty("id")
    private String api_id;

    /**
     * Сокращённое название монеты
     */
    @JsonProperty("symbol")
    private String symbol;

    /**
     * Название монеты
     */
    @JsonProperty("name")
    private String name;

    /**
     * Ссылка на изображение логотипа валюты
     */
    @JsonProperty("image")
    private String image;

    /**
     * Актуальная цена монеты
     */
    @JsonProperty("current_price")
    private double currentPrice;

    /**
     * Рыночная капитализация монеты
     */
    @JsonProperty("market_cap")
    private double marketCap;

    /**
     * Позиция в рейтинге по рыночной капитализации
     */
    @JsonProperty("market_cap_rank")
    private long marketCapRank;

    /**
     * Полностью разбавленная капитализация
     */
    @JsonProperty("fully_diluted_valuation")
    private double fullyDilutedValuation;

    /**
     * Объём торгов
     */
    @JsonProperty("total_volume")
    private double totalVolume;

    /**
     * Максимум за 24 часа
     */
    @JsonProperty("high_24h")
    private double high24h;

    /**
     * Минимум за 24 часа
     */
    @JsonProperty("low_24h")
    private double low24h;

    /**
     * Разница цены монеты за 24 часа
     */
    @JsonProperty("price_change_24h")
    private double priceChange24h;

    /**
     * Разница цены монеты за 24 часа в процентах
     */
    @JsonProperty("price_change_percentage_24h")
    private double priceChangePercentage24h;

    /**
     * Разница рыночной капитализации за 24 часа
     */
    @JsonProperty("market_cap_change_24h")
    private double marketCapChange24h;

    /**
     * Разница рыночной капитализации за 24 часа в процентах
     */
    @JsonProperty("market_cap_change_percentage_24h")
    private double marketCapChangePercentage24h;

    /**
     * Циркулирующее предложение монеты
     */
    @JsonProperty("circulating_supply")
    private double circulatingSupply;

    /**
     * Общее предложение монеты
     */
    @JsonProperty("total_supply")
    private double totalSupply;

    /**
     * Максимальный объём монеты
     */
    @JsonProperty("max_supply")
    private double maxSupply;

    /**
     * Значение исторического максимума монеты
     */
    @JsonProperty("ath")
    private double ath;

    /**
     * На сколько процентов изменилась монеты относительно исторического максимума
     */
    @JsonProperty("ath_change_percentage")
    private double athChangePercentage;

    /**
     * Дата исторического максимума цены монеты
     */
    @JsonProperty("ath_date")
    private DateTime athDate;

    /**
     * Значение исторического минимума монеты
     */
    @JsonProperty("atl")
    private double atl;

    /**
     * На сколько процентов изменилась монеты относительно исторического минимума
     */
    @JsonProperty("atl_change_percentage")
    private double atlChangePercentage;

    /**
     * Дата исторического минимума цены монеты
     */
    @JsonProperty("atl_date")
    private DateTime atlDate;

    /**
     * Дата последнего обновления информации о монете
     */
    @JsonProperty("last_updated")
    private DateTime lastUpdated;

}
