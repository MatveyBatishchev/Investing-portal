package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.CoinFullDto;
import ru.investing_portal.dto.CoinShortDto;
import ru.investing_portal.models.domain.Coin;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CoinMapper {

    CoinFullDto toFullDto(Coin coin);

    @Named("toShortDto")
    CoinShortDto toShortDto(Coin coin);

    // {categories, watchlists, webResources} are unmapped properties ↓↓↓
    @Mapping(target = "id", ignore = true) // during creating id will generate automatically
    Coin toCoin(CoinFullDto coinFullDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCoinFromDto(CoinFullDto coinFullDto, @MappingTarget Coin entity);

    @IterableMapping(qualifiedByName = "toShortDto")
    List<CoinShortDto> map(Set<Coin> coins);


}
