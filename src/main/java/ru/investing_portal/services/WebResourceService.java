package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.WebResourceDto;
import ru.investing_portal.mappers.WebResourceMapper;
import ru.investing_portal.models.domain.WebResource;
import ru.investing_portal.repos.WebResourceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebResourceService {

    private final WebResourceRepository webResourceRepository;

    private final WebResourceMapper webResourceMapper;

    private WebResource getWebResourceById(int id) {
        return webResourceRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Веб-ресурс с id " + id + " не был найден!"));
    }

    public void createWebResource(WebResourceDto webResourceDto) {
        webResourceRepository.save(webResourceMapper.toWebResource(webResourceDto));
    }

    public WebResourceDto findWebResourceById(int id) {
        return webResourceMapper.toDto(getWebResourceById(id));
    }

    public void updateWebResource(int id, WebResourceDto webResourceDto) {
        WebResource dbWebResource = getWebResourceById(id);
        webResourceDto.setId(id);
        webResourceMapper.updateWebResourceFromDto(webResourceDto, dbWebResource);
        webResourceRepository.save(dbWebResource);
    }

    public void deleteWebResourceById(int id) {
        webResourceRepository.deleteById(id);
    }

    public List<WebResourceDto> findAllWebResources(Integer pageNum, Integer perPage) {
        List<WebResource> webResources = webResourceRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return webResources.stream().map(webResourceMapper::toDto).collect(Collectors.toList());
    }

    public List<WebResourceDto> findWebResourcesByCoinId(int coinId) {
        return webResourceMapper.map(webResourceRepository.findByCoinId(coinId));
    }

}
