package ru.investing_portal.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="watchlist")
public class Watchlist {

    /**
     * Идентификатор watchlist-а
     */
    @Id
    @SequenceGenerator(name="watchlist_sequence", sequenceName = "watchlist_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="watchlist_sequence")
    @Column(name="id")
    private int id;

    /**
     * Название портфолио
     */
    @Column(name="name")
    private String name;

    /**
     * Описание watchlist-а
     */
    @Column(name="comments")
    private String comments;

    /**
     * Список монеты связанных с watchlist-ом
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name="watchlist_coin",
            joinColumns={@JoinColumn(name="watchlist_id")},
            inverseJoinColumns={@JoinColumn(name="coin_id")})
    private Set<Coin> coins = new HashSet<>();

}
