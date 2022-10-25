package ru.investing_portal.controllers;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.WebResourceDto;
import ru.investing_portal.mappers.WebResourceMapper;
import ru.investing_portal.models.domain.WebResource;
import ru.investing_portal.repos.WebResourceRepository;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@AllArgsConstructor
public class WebResourceControllerImpl implements WebResourceController {

    private final WebResourceRepository webResourceRepository;

    private final WebResourceMapper webResourceMapper;


    @Override
    public void create(WebResourceDto webResourceDto) {
        webResourceRepository.save(webResourceMapper.toWebResource(webResourceDto));
    }

    @Override
    public WebResourceDto read(int id) {
        return webResourceMapper.toDto(webResourceRepository.findById(id).get());
    }

    @Override
    public void update(int id, WebResourceDto webResourceDto) {
        WebResource dbWebResource = webResourceRepository.findById(id).get();
        webResourceDto.setId(id);
        webResourceMapper.updateWebResourceFromDto(webResourceDto, dbWebResource);
        webResourceRepository.save(dbWebResource);
    }

    @Override
    public void delete(int id) {
        webResourceRepository.deleteById(id);
    }

    @Override
    public List<WebResourceDto> readAll(Integer pageNum, Integer perPage) {
        List<WebResource> webResources = webResourceRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return webResources.stream().map(webResourceMapper::toDto).collect(Collectors.toList());
    }
}

