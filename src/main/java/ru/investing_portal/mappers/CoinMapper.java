package ru.investing_portal.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.investing_portal.dto.CoinFullDto;
import ru.investing_portal.dto.CoinShortDto;
import ru.investing_portal.models.domain.Coin;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CoinMapper {

    @Named("toCoinFullDto")
    CoinFullDto toFullDto(Coin coin);

    @Named("toCoinShortDto")
    CoinShortDto toShortDto(Coin coin);

    @IterableMapping(qualifiedByName = "toCoinShortDto")
    List<CoinShortDto> mapToShortDto(Collection<Coin> coins);

    @IterableMapping(qualifiedByName = "toCoinFullDto")
    List<CoinFullDto> mapToFullDto(Collection<Coin> coins);


}
