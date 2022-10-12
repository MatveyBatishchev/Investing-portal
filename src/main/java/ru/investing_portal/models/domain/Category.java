package ru.investing_portal.models.domain;

import lombok.Data;

@Data
public class Category {

    /**
     * Идентификатор категории
     */
    private int id;

    /**
     * Название категории
     */
    private String name;

}
