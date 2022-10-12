package ru.investing_portal.models.domain;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class FiatCurrency {

    /**
     * Идентификатор фиатной валюты
     */
    private int id;

    /**
     * Аббревиатура фиатной валюты
     */
    private String symbol;

    /**
     * Полное наименование фиатной валюты
     */
    private String name;

    /**
     * Обменный курс валюты относительно американского доллара
     */
    private double rate;

    /**
     * Дата последнего обновления информации о фиатной валюте
     */
    private DateTime lastUpdated;

}
