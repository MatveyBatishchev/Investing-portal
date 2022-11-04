package ru.investing_portal.models.domain;

import java.util.Set;

public class TransactionGroup {

    Coin coin;

    double holdings;

    double pricePercentageChange;

    double avgPrice;

    Set<Transaction> transactions;

}
