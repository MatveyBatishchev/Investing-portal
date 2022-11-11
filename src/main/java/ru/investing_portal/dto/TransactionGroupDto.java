package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionGroupDto {

    @Schema(example = "1")
    private int id;

    @Schema(example = "1")
    private int portfolioId;

    private CoinShortDto coinShortDto;

    @Schema(example = "$65168.74")
    private BigDecimal holdingsValue;

    @Schema(example = "3.8")
    private BigDecimal holdings;

    @Schema(example = "20762.14")
    private BigDecimal avgPrice;

    @Schema(example = "76896.132")
    private BigDecimal totalSpend;

    @Schema(example = "-4872.84")
    private BigDecimal priceChange;

    @Schema(example = "-6.92")
    private BigDecimal priceChangePercentage;

}
