package ru.investing_portal.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user", schema = "main")
public class User {

    @Id
    @SequenceGenerator(name="coin_sequence", sequenceName = "coin_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="coin_sequence")
    @Column(name="id")
    private int id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Portfolio> portfolios;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private Set<Watchlist> watchlists;

    /**
     * Имя пользователя
     */
    @Column(name="username")
    private String username;

    /**
     * Пароль пользователя
     */
    @Column(name="password")
    private String password;

    /**
     * Электронная почта пользователя
     */
    @Column(name="email")
    private String email;

    /**
     * Список ролей пользователя
     */
    @ElementCollection(targetClass = ApplicationRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private List<ApplicationRole> roles = new ArrayList<>();

}
