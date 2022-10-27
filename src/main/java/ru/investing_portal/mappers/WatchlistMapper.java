package ru.investing_portal.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.investing_portal.dto.WatchlistDto;
import ru.investing_portal.models.domain.Watchlist;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface WatchlistMapper {

    WatchlistDto toDto(Watchlist watchlist);

    // {coins} are unmapped properties ↓↓↓
    Watchlist toWatchlist(WatchlistDto watchlistDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWatchlistFromDto(WatchlistDto watchlistDto, @MappingTarget Watchlist entity);
}

