package ru.investing_portal.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionGroupDto {

    private int id;

    private int portfolioId;

    private CoinShortDto coinShortDto;

    private BigDecimal holdingsValue;

    private double holdings;

    private BigDecimal avgPrice;

    private BigDecimal totalSpend;

    private double priceChange;

    private double priceChangePercentage;

}
