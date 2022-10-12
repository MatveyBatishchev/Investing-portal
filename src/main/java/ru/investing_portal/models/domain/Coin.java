package ru.investing_portal.models.domain;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Coin {

    /**
     * Идентификатор монеты
     */
    private int id;

    /**
     * Идентификатор монеты в api CoinGeko
     */
    private String api_id;

    /**
     * Сокращённое название монеты
     */
    private String symbol;

    /**
     * Название монеты
     */
    private String name;

    /**
     * Ссылка на изображение логотипа валюты
     */
    private String image;

    /**
     * Актуальная цена монеты
     */
    private double currentPrice;

    /**
     * Рыночная капитализация монеты
     */
    private double marketCap;

    /**
     * Позиция в рейтинге по рыночной капитализации
     */
    private long marketCapRank;

    /**
     * Полностью разбавленная капитализация
     */
    private double fullyDilutedValuation;

    /**
     * Объём торгов
     */
    private double totalVolume;

    /**
     * Максимум за 24 часа
     */
    private double high24h;

    /**
     * Минимум за 24 часа
     */
    private double low24h;

    /**
     * Разница цены монеты за 24 часа
     */
    private double priceChange24h;

    /**
     * Разница цены монеты за 24 часа в процентах
     */
    private double priceChangePercentage24h;

    /**
     * Разница рыночной капитализации за 24 часа
     */
    private double marketCapChange24h;

    /**
     * Разница рыночной капитализации за 24 часа в процентах
     */
    private double marketCapChangePercentage24h;

    /**
     * Циркулирующее предложение монеты
     */
    private double circulatingSupply;

    /**
     * Общее предложение монеты
     */
    private double totalSupply;

    /**
     * Значение исторического максимума монеты
     */
    private double ath;

    /**
     * На сколько процентов изменилась монеты относительно исторического максимума
     */
    private double athChangePercentage;

    /**
     * Дата исторического максимума цены монеты
     */
    private DateTime athDate;

    /**
     * Значение исторического минимума монеты
     */
    private double atl;

    /**
     * На сколько процентов изменилась монеты относительно исторического минимума
     */
    private double atlChangePercentage;

    /**
     * Дата исторического минимума цены монеты
     */
    private DateTime atlDate;

    /**
     * Дата последнего обновления информации о монете
     */
    private DateTime lastUpdated;

}
