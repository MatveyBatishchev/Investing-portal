package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.WatchlistCreateDto;
import ru.investing_portal.dto.WatchlistReadDto;
import ru.investing_portal.models.domain.Watchlist;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class, uses = CoinMapper.class)
public interface WatchlistMapper {
    
    WatchlistReadDto toDto(Watchlist watchlist);

    // {coins} are unmapped properties ↓↓↓
    @Mapping(target = "id", ignore = true) // during creating id will generate automatically
    Watchlist toWatchlist(WatchlistCreateDto watchlistCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWatchlistFromDto(WatchlistCreateDto watchlistCreateDto, @MappingTarget Watchlist entity);
}

