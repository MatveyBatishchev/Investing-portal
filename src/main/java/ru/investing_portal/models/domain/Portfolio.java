package ru.investing_portal.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="portfolio")
public class Portfolio {

    /**
     * Идентификатор портфолио
     */
    @Id
    @SequenceGenerator(name="portfolio_sequence", sequenceName = "portfolio_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="portfolio_sequence")
    @Column(name="id")
    private int id;

    /**
     * Название портфолио
     */
    @Column(name="name")
    private String name;

    /**
     * Группы транзакций связанные с портфолио
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="portfolio")
    private Set<TransactionGroup> transactionGroups = new HashSet<>();

    /**
     * Комментарии к портфолио
     */
    @Column(name="comments")
    private String comments;

    /**
     * Баланс портфолио
     */
    @Transient
    private BigDecimal totalBalance;

    /**
     * Баланс портфолио 24 назад
     */
    @Column(name="balance_24h")
    private BigDecimal balance24h;




}
