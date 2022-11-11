package ru.investing_portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.joda.time.DateTime;
import ru.investing_portal.models.domain.TransactionType;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class TransactionReadDto {

    @Schema(example = "1")
    private int id;

    @Schema(example = "1")
    private int transactionGroupId;

    @Schema(example = "20564.88")
    private BigDecimal pricePerCoin;

    @Schema(example = "3.8")
    private BigDecimal amount;

    @Schema(example = "32802.56")
    private BigDecimal sum;

    @Schema(description = "Дата и время совершения транзакции (pastOrPresent)", example = "2022-10-27T13:56:56.038Z")
    @PastOrPresent
    private DateTime date;

    @Schema(example = "BUY")
    private TransactionType transactionType;

    @Schema(description = "Налог на операцию (только в долларах)", example = "812")
    private BigDecimal fees;

    @Schema(example = "Транзация № 341142 на Binance")
    @Size(max = 160)
    private String comments;


}
