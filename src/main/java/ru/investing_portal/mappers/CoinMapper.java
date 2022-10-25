package ru.investing_portal.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.investing_portal.dto.CoinFullDto;
import ru.investing_portal.models.domain.Coin;

@Mapper(componentModel = "spring")
public interface CoinMapper {

    CoinFullDto toDto(Coin coin);

    Coin toCoin(CoinFullDto coinFullDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCoinFromDto(CoinFullDto coinFullDto, @MappingTarget Coin entity);


    default Coin fromCoinId(Integer id) {
        if (id == null) return null;
        Coin coin = new Coin();
        coin.setId(id);
        return coin;
    }

}
