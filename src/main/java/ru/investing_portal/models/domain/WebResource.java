package ru.investing_portal.models.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="web_resource")
public class WebResource {

    /**
     * Идентификатор веб-ресурса
     */
    @Id
    @SequenceGenerator(name="web_resource_sequence", sequenceName = "web_resource_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="web_resource_sequence")
    @Column(name="id")
    private int id;

    /**
     * Идентификатор монеты, связанной с веб-ресурсом
     */
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

    /**
     * Constructor without ID
     */
    // FIXME: Try to generate this constructor with lombok -> see about entity generated id, while saving
    public WebResource(Coin coin, String name, String url, WebResourceType webResourceType) {
        this.coin = coin;
        this.name = name;
        this.url = url;
        this.webResourceType = webResourceType;
    }

}
