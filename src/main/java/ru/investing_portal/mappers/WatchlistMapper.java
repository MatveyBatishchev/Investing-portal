package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.WatchlistDto;
import ru.investing_portal.models.domain.Watchlist;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface WatchlistMapper {

    WatchlistDto toDto(Watchlist watchlist);

    // {coins} are unmapped properties ↓↓↓
    @Mapping(target = "id", ignore = true) // during creating id will generate automatically
    Watchlist toWatchlist(WatchlistDto watchlistDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWatchlistFromDto(WatchlistDto watchlistDto, @MappingTarget Watchlist entity);
}

