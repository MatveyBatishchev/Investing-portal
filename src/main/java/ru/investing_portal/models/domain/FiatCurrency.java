package ru.investing_portal.models.domain;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name="fiat-currency")
public class FiatCurrency {

    /**
     * Идентификатор фиатной валюты
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /**
     * Аббревиатура фиатной валюты
     */
    @Column(name="symbol")
    private String symbol;

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
    @Column(name="lastUpdated")
    private DateTime lastUpdated;

}
