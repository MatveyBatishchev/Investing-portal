package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class PortfolioFullDto {

    @Schema(example = "1", required = true)
    private int id;

    @Schema(example = "Моё главное портфолио", required = true)
    @Size(max=36)
    private String name;

    @Schema(example = "Портфолио с основными траназакциями на binance", required = false)
    @Size()
    private String comments;

    private BigDecimal totalBalance;

    private BigDecimal totalProfitLoss;

    private Map<String, BigDecimal> allocation;

    private BigDecimal balanceChange24h;

    private BigDecimal balanceChangePercentage24h;

    private int bestPerformerId;

    private int worstPerformerId;

}
