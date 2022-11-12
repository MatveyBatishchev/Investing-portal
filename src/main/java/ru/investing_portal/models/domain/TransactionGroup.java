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
@Table(name="transaction_group")
public class TransactionGroup {

    /**
     * Идентификатор группы транзакций
     */
    @Id
    @SequenceGenerator(name="transaction_group_sequence", sequenceName = "transaction_group_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="transaction_group_sequence")
    @Column(name="id")
    private int id;

    /**
     * Портфолио, в котором находится группа транзакций
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="portfolio_id")
    private Portfolio portfolio;

    /**
     * Монета, связанная с группой транзакцией
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="coin_id")
    private Coin coin;

    /**
     * Транзакции, которые находятся в группе
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="transactionGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    /**
     * Стоимость всех владений
     */
    @Column(name="holdings_value")
    private BigDecimal holdingsValue;

    /**
     * Кол-во всех монет в обращении (может быть отрицательным значением)
     */
    @Column(name="holdings")
    private BigDecimal holdings;

    /**
     * Средняя цена покупки
     */
    @Column(name="avg_price")
    private BigDecimal avgPrice;

    /**
     * Сумма потраченная на все транзакции
     */
    @Column(name="total_spend")
    private BigDecimal totalSpend;

    /**
     * Изменения стоимости владений с момента покупки
     */
    @Column(name="price_change")
    private BigDecimal priceChange;

    /**
     * Процент изменения стоимости владений с момента покупки
     */
    @Column(name="price_change_percentage")
    private BigDecimal priceChangePercentage;

}
