package ru.investing_portal.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * Constructor without ID
     */
    // FIXME: Try to generate this constructor with lombok -> see about entity generated id, while saving
    public Transaction(Portfolio portfolio, double pricePerCoin, double amount, double sum, DateTime date, TransactionType transactionType, double fees, String comments) {
        this.portfolio = portfolio;
        this.pricePerCoin = pricePerCoin;
        this.amount = amount;
        this.sum = sum;
        this.date = date;
        this.transactionType = transactionType;
        this.fees = fees;
        this.comments = comments;
    }

}
