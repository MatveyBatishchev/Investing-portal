package ru.investing_portal.models.domain;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CoinCategory {

    /**
     * Идентификатор монеты
     */
    @Column(name="coin_id")
    private int coinID;

    /**
     * Идентификатор категории
     */
    @Column(name="category_id")
    private int categoryID;

}
