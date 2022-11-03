package ru.investing_portal.models.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name="fiat_currency")
public class FiatCurrency {

    /**
     * Идентификатор фиатной валюты
     */
    @Schema(example = "1", required = true)
    @Id
    @SequenceGenerator(name="fiat_currency_sequence", sequenceName = "fiat_currency_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fiat_currency_sequence")
    @Column(name="id")
    private int id;

    /**
     * Аббревиатура фиатной валюты
     */
    @Schema(example = "USD", required = true)
    @Column(name="code")
    @Size(max = 5)
    private String code;

    /**
     * Символ фиатной валюты, например $
     */
    @Schema(example = "$", required = true)
    @Column(name="symbol")
    @Size(max = 4)
    private String symbol;

    /**
     * Ссылка на изображение с логотипом флага страны
     */
    @Schema(example = "https://s2.coinmarketcap.com/static/cloud/img/fiat-flags/BRL.svg", required = true)
    @Column(name="image")
    @Size(max = 255)
    private String image;

    /**
     * Полное наименование фиатной валюты
     */
    @Schema(example = "United States Dollar", required = true)
    @Column(name="name")
    @Size(max = 36)
    private String name;

    /**
     * Обменный курс валюты относительно американского доллара
     */
    @Schema(example = "3.672985", required = true)
    @Column(name="rate")
    private BigDecimal rate;

    /**
     * Дата последнего обновления информации о фиатной валюте
     */
    @Schema(example = "2022-10-27T15:52:56.261Z", required = true)
    @Column(name="last_updated")
    private DateTime lastUpdated;

}
