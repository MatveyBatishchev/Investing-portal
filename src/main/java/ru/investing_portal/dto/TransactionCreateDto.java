package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.joda.time.DateTime;
import ru.investing_portal.models.domain.TransactionType;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class TransactionCreateDto {

    @Schema(example = "1", required = true)
    private int id;

    @Schema(example = "1", required = true)
    private int portfolioId;

    @Schema(example = "1", required = true)
    private int coinId;

    @Schema(example = "20564.88", required = true)
    private BigDecimal pricePerCoin;

    @Schema(example = "3.8", required = true)
    private BigDecimal amount;

    @Schema(example = "32802.56", required = true)
    private BigDecimal sum;

    @Schema(description = "Дата и время совершения транзакции (pastOrPresent)", example = "2022-10-27T13:56:56.038Z", required = true)
    @PastOrPresent
    private DateTime date;

    @Schema(example = "BUY", required = true)
    private TransactionType transactionType;

    @Schema(description = "Налог на операцию (только в долларах)", example = "812", required = false)
    private BigDecimal fees;

    @Schema(example = "Транзация № 341142 на Binance", required = false)
    @Size(max = 160)
    private String comments;

}

