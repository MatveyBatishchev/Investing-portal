package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.WebResourceDto;
import ru.investing_portal.models.domain.WebResource;
import ru.investing_portal.repos.CoinRepository;


@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class, uses = { CoinRepository.class })
public interface WebResourceMapper {

    @Mapping(target="coinId", expression = "java(webResource.getCoin().getId())")
    WebResourceDto toDto(WebResource webResource);

    @Mapping(target = "id", ignore = true) // during creating id will generate automatically
    @Mapping(target="coin", source = "coinId", qualifiedByName = "getCoinReferenceById")
    WebResource toWebResource(WebResourceDto webResourceDto);

    @Mapping(target="coin", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWebResourceFromDto(WebResourceDto webResourceDto, @MappingTarget WebResource entity);

}
