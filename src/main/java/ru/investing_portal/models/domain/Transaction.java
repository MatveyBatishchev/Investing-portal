package ru.investing_portal.models.domain;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name="transaction")
public class Transaction {

    /**
     * Идентификатор транзакции
     */
    @Id
    @SequenceGenerator(name="transaction_sequence", sequenceName = "transaction_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="transaction_sequence")
    @Column(name="id")
    private int id;

    /**
     * Портфолио, в котором была совершена транзакция
     */
    @ManyToOne
    @JoinColumn(name="portfolio_id")
    private Portfolio portfolio;

    /**
     * Монета, связанная с транзакцией
     */
    @ManyToOne
    @JoinColumn(name="coin_id")
    private Coin coin;

    /**
     * Цена за единику криптомонеты
     */
    @Column(name="price_per_coin")
    private double pricePerCoin;

    /**
     * Кол-во купленных монет
     */
    @Column(name="amount")
    private double amount;

    /**
     * Конечная сумма транзакции
     */
    @Column(name="sum")
    private double sum;

    /**
     * Дата совершения транзакции
     */
    @Column(name="date")
    private DateTime date;

    /**
     * Тип транзакции
     */
    @Column(name="type")
    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    /**
     * Сумма запалченного налога во время совершения транзакции
     */
    @Column(name="fees")
    private double fees;

    /**
     * Комментарий к транзакции
     */
    @Column(name="comments")
    private String comments;

}
