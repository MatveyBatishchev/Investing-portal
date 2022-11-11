package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class PortfolioFullDto {

    @Schema(example = "1")
    private int id;

    @Schema(example = "Моё главное портфолио")
    private String name;

    @Schema(example = "Портфолио с основными траназакциями на binance")
    private String comments;

    @Schema(example = "Общий баланс портфолио")
    private BigDecimal totalBalance;

    @Schema(example = "Общий прирост / потеря баланса портфолио")
    private BigDecimal totalProfitLoss;

    @Schema(example = "Распределение доли каждой монеты в портфеле")
    private Map<String, BigDecimal> allocation;

    @Schema(example = "Изменение баланса портфолио за 24 часа")
    private BigDecimal balanceChange24h;

    @Schema(example = "Изменение баланса портфолио в процентах за 24 часа")
    private BigDecimal balanceChangePercentage24h;

    @Schema(example = "Идентификатор группы транзакций с лучшей динамикой в портфолио")
    private int bestPerformerId;

    @Schema(example = "Идентификатор группы транзакций с худшей динамикой в портфолио")
    private int worstPerformerId;

}
