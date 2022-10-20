package ru.investing_portal.models.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name="web_resource")
public class WebResource {

    /**
     * Идентификатор веб-ресурса
     */
    @Id
    @SequenceGenerator(name="web_resource_sequence", sequenceName = "web_resource_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="web_resource_sequence")
    @Column(name="id")
    private int id;

    /**
     * Идентификатор монеты, связанной с веб-ресурсом
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name="coin_id")
    private Coin coin;

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
    @Enumerated(EnumType.ORDINAL)
    private WebResourceType webResourceType;

}
