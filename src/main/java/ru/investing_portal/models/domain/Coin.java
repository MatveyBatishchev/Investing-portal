package ru.investing_portal.models.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="coin")
public class Coin {

    /**
     * Идентификатор монеты
     */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /**
     * Идентификатор монеты в api CoinGeko
     */
    @JsonProperty("id")
    @Column(name="api_id")
    private String api_id;

    /**
     * Сокращённое название монеты
     */
    @JsonProperty("symbol")
    @Column(name="symbol")
    private String symbol;

    /**
     * Название монеты
     */
    @JsonProperty("name")
    @Column(name="name")
    private String name;

    /**
     * Ссылка на изображение логотипа валюты
     */
    @JsonProperty("image")
    @Column(name="image")
    private String image;

    /**
     * Актуальная цена монеты
     */
    @JsonProperty("current_price")
    @Column(name="current_price")
    private double currentPrice;

    /**
     * Рыночная капитализация монеты
     */
    @JsonProperty("market_cap")
    @Column(name="market_cap")
    private double marketCap;

    /**
     * Позиция в рейтинге по рыночной капитализации
     */
    @JsonProperty("market_cap_rank")
    @Column(name="market_cap_rank")
    private long marketCapRank;

    /**
     * Полностью разбавленная капитализация
     */
    @JsonProperty("fully_diluted_valuation")
    @Column(name="fully_diluted_valuation")
    private double fullyDilutedValuation;

    /**
     * Объём торгов
     */
    @JsonProperty("total_volume")
    @Column(name="total_volume")
    private double totalVolume;

    /**
     * Максимум за 24 часа
     */
    @JsonProperty("high_24h")
    @Column(name="high_24h")
    private double high24h;

    /**
     * Минимум за 24 часа
     */
    @JsonProperty("low_24h")
    @Column(name="low_24h")
    private double low24h;

    /**
     * Разница цены монеты за 24 часа
     */
    @JsonProperty("price_change_24h")
    @Column(name="price_change_24h")
    private double priceChange24h;

    /**
     * Разница цены монеты за 24 часа в процентах
     */
    @JsonProperty("price_change_percentage_24h")
    @Column(name="price_change_percentage_24h")
    private double priceChangePercentage24h;

    /**
     * Разница рыночной капитализации за 24 часа
     */
    @JsonProperty("market_cap_change_24h")
    @Column(name="market_cap_change_24h")
    private double marketCapChange24h;

    /**
     * Разница рыночной капитализации за 24 часа в процентах
     */
    @JsonProperty("market_cap_change_percentage_24h")
    @Column(name="market_cap_change_percentage_24h")
    private double marketCapChangePercentage24h;

    /**
     * Циркулирующее предложение монеты
     */
    @JsonProperty("circulating_supply")
    @Column(name="circulating_supply")
    private double circulatingSupply;

    /**
     * Общее предложение монеты
     */
    @JsonProperty("total_supply")
    @Column(name="total_supply")
    private double totalSupply;

    /**
     * Максимальный объём монеты
     */
    @JsonProperty("max_supply")
    @Column(name="max_supply")
    private double maxSupply;

    /**
     * Значение исторического максимума монеты
     */
    @JsonProperty("ath")
    @Column(name="ath")
    private double ath;

    /**
     * На сколько процентов изменилась монеты относительно исторического максимума
     */
    @JsonProperty("ath_change_percentage")
    @Column(name="ath_change_percentage")
    private double athChangePercentage;

    /**
     * Дата исторического максимума цены монеты
     */
    @JsonProperty("ath_date")
    @Column(name="ath_date")
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime athDate;

    /**
     * Значение исторического минимума монеты
     */
    @JsonProperty("atl")
    @Column(name="atl")
    private double atl;

    /**
     * На сколько процентов изменилась монеты относительно исторического минимума
     */
    @JsonProperty("atl_change_percentage")
    @Column(name="atl_change_percentage")
    private double atlChangePercentage;

    /**
     * Дата исторического минимума цены монеты
     */
    @JsonProperty("atl_date")
    @Column(name="atl_date")
    private DateTime atlDate;

    /**
     * Дата последнего обновления информации о монете
     */
    @JsonProperty("last_updated")
    @Column(name="last_updated")
    private DateTime lastUpdated;

}
