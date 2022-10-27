package ru.investing_portal.models.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="fiat_currency")
public class FiatCurrency {

    /**
     * Идентификатор фиатной валюты
     */
    @Id
    @SequenceGenerator(name="fiat_currency_sequence", sequenceName = "fiat_currency_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fiat_currency_sequence")
    @Column(name="id")
    private int id;

    /**
     * Аббревиатура фиатной валюты
     */
    @Column(name="code")
    private String code;

    /**
     * Символ фиатной валюты, например $
     */
    @Column(name="symbol")
    private String symbol;

    /**
     * Ссылка на изображение с логотипом флага страны
     */
    @Column(name="image")
    private String image;

    /**
     * Полное наименование фиатной валюты
     */
    @Column(name="name")
    private String name;

    /**
     * Обменный курс валюты относительно американского доллара
     */
    @Column(name="rate")
    private double rate;

    /**
     * Дата последнего обновления информации о фиатной валюте
     */
    @Column(name="last_updated")
    private DateTime lastUpdated;

}
