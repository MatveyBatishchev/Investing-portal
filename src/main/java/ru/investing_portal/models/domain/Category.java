package ru.investing_portal.models.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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
     * Список монеты связанных с категорией
     */
    @ManyToMany
    @JoinTable(name="coin_category",
            joinColumns={@JoinColumn(name="coin_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")})
    private Set<Coin> coin;

    /**
     * Название категории
     */
    @Column(name="name")
    private String name;

}
