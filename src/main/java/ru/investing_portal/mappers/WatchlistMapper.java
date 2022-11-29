package ru.investing_portal.mappers;

import org.mapstruct.*;
import ru.investing_portal.dto.WatchlistDto;
import ru.investing_portal.models.domain.Watchlist;
import ru.investing_portal.repos.UserRepository;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class, uses = {CoinMapper.class, UserRepository.class})
public interface WatchlistMapper {

    @Named("toWatchlistDto")
    WatchlistDto toDto(Watchlist watchlist);

    // {coins} are unmapped properties ↓↓↓
    @Mapping(target = "id", ignore = true) // during creating id will generate automatically
    @Mapping(target="user", source="userId", qualifiedByName = "getUserReferenceById")
    Watchlist toWatchlist(WatchlistDto watchlistDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateWatchlistFromDto(WatchlistDto watchlistDto, @MappingTarget Watchlist entity);

    @IterableMapping(qualifiedByName = "toWatchlistDto")
    List<WatchlistDto> map(Collection<Watchlist> watchlists);

}

