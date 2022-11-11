package ru.investing_portal.models.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name="fiat_currency")
public class FiatCurrency {

    /**
     * Идентификатор фиатной валюты
     */
    @Schema(example = "1")
    @Id
    @SequenceGenerator(name="fiat_currency_sequence", sequenceName = "fiat_currency_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fiat_currency_sequence")
    @Column(name="id")
    private int id;

    /**
     * Аббревиатура фиатной валюты
     */
    @Schema(example = "USD")
    @Column(name="code")
    private String code;

    /**
     * Символ фиатной валюты, например $
     */
    @Schema(example = "$")
    @Column(name="symbol")
    private String symbol;

    /**
     * Ссылка на изображение с логотипом флага страны
     */
    @Schema(example = "https://s2.coinmarketcap.com/static/cloud/img/fiat-flags/BRL.svg")
    @Column(name="image")
    private String image;

    /**
     * Полное наименование фиатной валюты
     */
    @Schema(example = "United States Dollar")
    @Column(name="name")
    private String name;

    /**
     * Обменный курс валюты относительно американского доллара
     */
    @Schema(example = "3.672985")
    @Column(name="rate")
    private BigDecimal rate;

    /**
     * Дата последнего обновления информации о фиатной валюте
     */
    @Schema(example = "2022-10-27T15:52:56.261Z")
    @Column(name="last_updated")
    private DateTime lastUpdated;

}
