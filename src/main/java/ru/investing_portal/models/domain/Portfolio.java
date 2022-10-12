package ru.investing_portal.models.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="portfolio")
public class Portfolio {

    /**
     * Идентификатор портфолио
     */
    @Id
    private int id;

    /**
     * Название портфолио
     */
    private String name;

}
