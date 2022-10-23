package ru.investing_portal.dto;

import lombok.Data;
import org.joda.time.DateTime;
import ru.investing_portal.models.domain.TransactionType;

@Data
public class TransactionDto {

    private int id;

    private int portfolioId;

    private int coinId;

    private double pricePerCoin;

    private double amount;

    private double sum;

    private DateTime date;

    private TransactionType transactionType;

    private double fees;

    private String comments;

}
