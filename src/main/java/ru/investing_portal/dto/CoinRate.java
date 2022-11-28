package ru.investing_portal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoinRate {

    @JsonProperty("USD")
    private BigDecimal coinRate;

}
