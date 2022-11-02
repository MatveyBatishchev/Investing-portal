package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.WatchlistDto;
import ru.investing_portal.mappers.WatchlistMapper;
import ru.investing_portal.models.domain.Watchlist;
import ru.investing_portal.repos.WatchlistRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;

    private final WatchlistMapper watchlistMapper;

    private Watchlist getWatchlistById(int id) {
        return watchlistRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Избранный лист с id " + id + " не был найден!"));
    }

    public void createWatchlist(WatchlistDto watchlistDto) {
        watchlistRepository.save(watchlistMapper.toWatchlist(watchlistDto));
    }

    public WatchlistDto findWatchlistById(int id) {
        return watchlistMapper.toDto(getWatchlistById(id));
    }

    public void updateWatchlist(int id, WatchlistDto watchlistDto) {
        Watchlist dbWatchlist = getWatchlistById(id);
        watchlistDto.setId(id);
        watchlistMapper.updateWatchlistFromDto(watchlistDto, dbWatchlist);
        watchlistRepository.save(dbWatchlist);
    }

    public void deleteWatchlistById(int id) {
        watchlistRepository.deleteById(id);
    }

    public List<WatchlistDto> findAllWatchlists(Integer pageNum, Integer perPage) {
        List<Watchlist> portfolios = watchlistRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return portfolios.stream().map(watchlistMapper::toDto).collect(Collectors.toList());
    }

}
