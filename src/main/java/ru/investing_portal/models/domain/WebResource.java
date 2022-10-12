package ru.investing_portal.models.domain;

import lombok.Data;

@Data
public class WebResource {

    /**
     * Идентификатор веб-ресурса
     */
    private int id;

    /**
     * Идентификатор монеты, связанной с веб-ресурсом
     */
    private int coinId;

    /**
     * Названеи веб ресурса, которое будет отобрааться гиперссылкой
     */
    private String name;

    /**
     * Ссылка на непосредственный ресурс
     */
    private String url;

    /**
     * Тип веб-ресурса
     */
    private WebResourceType webResourceType;

}
