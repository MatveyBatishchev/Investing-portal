package ru.investing_portal.models.domain;

import lombok.Data;

@Data
public class CoinCategory {

    /**
     * Идентификатор монеты
     */
    private int coinID;

    /**
     * Идентификатор категории
     */
    private int categoryID;

}
