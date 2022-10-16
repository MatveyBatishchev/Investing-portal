package ru.investing_portal.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="category")
public class Category {

    /**
     * Идентификатор категории
     */
    @Id
    @SequenceGenerator(name="category_sequence", sequenceName = "category_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_sequence")
    @Column(name="id")
    private int id;

    /**
     * Название категории
     */
    @Column(name="name")
    private String name;

}
