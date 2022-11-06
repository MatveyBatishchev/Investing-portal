package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.PortfolioDto;
import ru.investing_portal.models.domain.Portfolio;

import java.util.List;


@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface PortfolioMapper {

    PortfolioDto toDto(Portfolio portfolio);

    @Mapping(target = "id", ignore = true) // during creating id will generate automatically
    Portfolio toPortfolio(PortfolioDto portfolioDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePortfolioFromDto(PortfolioDto portfolioDto, @MappingTarget Portfolio entity);

    List<PortfolioDto> map(List<Portfolio> portfolios);

}