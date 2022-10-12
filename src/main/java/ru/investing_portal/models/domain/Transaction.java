package ru.investing_portal.models.domain;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Transaction {

    private int id;

    private int portfolio_id;

    private double pricePerCoin;

    private double amount;

    private double sum;

    private DateTime date;

    private TransactionType transactionType;

    private double fees;

    private String text;

}
