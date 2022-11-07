package ru.investing_portal.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="transaction")
public class Transaction {

    /**
     * Идентификатор транзакции
     */
    @Id
    @SequenceGenerator(name="transaction_sequence", sequenceName = "transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="transaction_sequence")
    @Column(name="id")
    private int id;

    /**
     * Группа транзакций, с которой связана транзакция
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name="transaction_group_id")
    private TransactionGroup transactionGroup;

    /**
     * Цена за единику криптомонеты
     */
    @Column(name="price_per_coin")
    private BigDecimal pricePerCoin;

    /**
     * Кол-во купленных монет
     */
    @Column(name="amount")
    private double amount;

    /**
     * Конечная сумма транзакции
     */
    @Column(name="sum")
    private BigDecimal sum;

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
    private BigDecimal fees;

    /**
     * Комментарий к транзакции
     */
    @Column(name="comments")
    private String comments;

}
