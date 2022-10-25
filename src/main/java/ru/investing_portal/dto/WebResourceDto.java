package ru.investing_portal.dto;

import lombok.Data;
import ru.investing_portal.models.domain.WebResourceType;

@Data
public class WebResourceDto {

    private int id;

    private int coinId;

    private String name;

    private String url;

    private WebResourceType webResourceType;

}
