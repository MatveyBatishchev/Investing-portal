package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.WebResourceDto;
import ru.investing_portal.services.WebResourceService;

import java.util.List;

@RestController
@AllArgsConstructor
public class WebResourceControllerImpl implements WebResourceController {

    private final WebResourceService webResourceService;


    @Override
    public void create(WebResourceDto webResourceDto) {
        webResourceService.createWebResource(webResourceDto);
    }

    @Override
    public WebResourceDto read(int id) {
        return webResourceService.findWebResourceById(id);
    }

    @Override
    public void update(int id, WebResourceDto webResourceDto) {
        webResourceService.updateWebResource(id, webResourceDto);
    }

    @Override
    public void delete(int id) {
        webResourceService.deleteWebResourceById(id);
    }

    @Override
    public List<WebResourceDto> readAll(Integer pageNum, Integer perPage) {
        return webResourceService.findAllWebResources(pageNum, perPage);
    }

    @Override
    public List<WebResourceDto> readByCoinId(int coinId) {
        return webResourceService.findWebResourcesByCoinId(coinId);
    }

}

