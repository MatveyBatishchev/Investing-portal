package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.PortfolioFullDto;
import ru.investing_portal.dto.PortfolioShortDto;
import ru.investing_portal.models.domain.Portfolio;

import java.util.List;


@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class, uses = {TransactionMapper.class})
public interface PortfolioMapper {

    PortfolioFullDto toFullDto(Portfolio portfolio);

    @Named("toPortfolioShortDto")
    PortfolioShortDto toShortDto(Portfolio portfolio);

    @Mapping(target = "id", ignore = true) // during creating id will generate automatically
    @Mapping(target = "totalBalance", ignore = true)
    @Mapping(target = "balance24h", constant = "0.0")
    Portfolio toPortfolio(PortfolioShortDto portfolioShortDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePortfolioFromDto(PortfolioShortDto portfolioShortDto, @MappingTarget Portfolio entity);

    @IterableMapping(qualifiedByName = "toPortfolioShortDto")
    List<PortfolioShortDto> map(List<Portfolio> portfolios);

}