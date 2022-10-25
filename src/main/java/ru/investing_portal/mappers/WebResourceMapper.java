package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.WebResourceDto;
import ru.investing_portal.models.domain.WebResource;
import ru.investing_portal.repos.CoinRepository;


@Mapper(componentModel = "spring", uses = { CoinRepository.class })
public interface WebResourceMapper {

    @Mapping(target="coinId", expression = "java(webResource.getCoin().getId())")
    WebResourceDto toDto(WebResource webResource);

    @Mapping(target="coin", source = "coinId", qualifiedByName = "getReferenceById")
    WebResource toWebResource(WebResourceDto webResourceDto);

    @Mapping(target="coin", source = "coinId", qualifiedByName = "getReferenceById")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWebResourceFromDto(WebResourceDto webResourceDto, @MappingTarget WebResource entity);

}
