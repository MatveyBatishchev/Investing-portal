package ru.investing_portal.models.domain;

import lombok.Data;

@Data
public class Portfolio {

    /**
     * Идентификатор портфолио
     */
    private int id;

    /**
     * Название портфолио
     */
    private String name;

}
