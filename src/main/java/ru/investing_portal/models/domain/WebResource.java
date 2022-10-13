package ru.investing_portal.models.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="web-resource")
public class WebResource {

    /**
     * Идентификатор веб-ресурса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /**
     * Идентификатор монеты, связанной с веб-ресурсом
     */
    @Column(name="coin_id")
    private int coinId;

    /**
     * Название веб ресурса, которое будет отобрааться гиперссылкой
     */
    @Column(name="name")
    private String name;

    /**
     * Ссылка на непосредственный ресурс
     */
    @Column(name="url")
    private String url;

    /**
     * Тип веб-ресурса
     */
    @Column(name="type")
    private WebResourceType webResourceType;

}
