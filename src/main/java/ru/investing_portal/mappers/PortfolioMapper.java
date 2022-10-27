package ru.investing_portal.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.investing_portal.dto.PortfolioDto;
import ru.investing_portal.models.domain.Portfolio;


@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface PortfolioMapper {

    PortfolioDto toDto(Portfolio portfolio);

    // {transactions} are unmapped properties ↓↓↓
    Portfolio toPortfolio(PortfolioDto portfolioDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePortfolioFromDto(PortfolioDto portfolioDto, @MappingTarget Portfolio entity);
}