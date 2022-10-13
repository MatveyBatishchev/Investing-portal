package ru.investing_portal.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="portfolio")
public class Portfolio {

    /**
     * Идентификатор портфолио
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /**
     * Название портфолио
     */
    @Column(name="name")
    private String name;

}
