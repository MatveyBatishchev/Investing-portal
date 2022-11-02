package ru.investing_portal.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="category")
public class Category {

    /**
     * Идентификатор категории
     */
    @Id
    @SequenceGenerator(name="category_sequence", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_sequence")
    @Column(name="id")
    private int id;

    /**
     * Список монеты связанных с категорией
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
//    @Cascade({
//            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//            org.hibernate.annotations.CascadeType.MERGE,
//            org.hibernate.annotations.CascadeType.PERSIST
//    })
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="coin_category",
            joinColumns={@JoinColumn(name="category_id")},
            inverseJoinColumns={@JoinColumn(name="coin_id")})
    private Set<Coin> coins = new HashSet<>();

    /**
     * Название категории
     */
    @Column(name="name")
    private String name;

}
