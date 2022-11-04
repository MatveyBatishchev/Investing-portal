package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.WatchlistCreateDto;
import ru.investing_portal.dto.WatchlistReadDto;
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

    public void createWatchlist(WatchlistCreateDto watchlistCreateDto) {
        watchlistRepository.save(watchlistMapper.toWatchlist(watchlistCreateDto));
    }

    public WatchlistReadDto findWatchlistById(int id) {
        return watchlistMapper.toDto(getWatchlistById(id));
    }

    public void updateWatchlist(int id, WatchlistCreateDto watchlistCreateDto) {
        Watchlist dbWatchlist = getWatchlistById(id);
        watchlistCreateDto.setId(id);
        watchlistMapper.updateWatchlistFromDto(watchlistCreateDto, dbWatchlist);
        watchlistRepository.save(dbWatchlist);
    }

    public void deleteWatchlistById(int id) {
        watchlistRepository.deleteById(id);
    }

    public List<WatchlistReadDto> findAllWatchlists(Integer pageNum, Integer perPage) {
        List<Watchlist> portfolios = watchlistRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return portfolios.stream().map(watchlistMapper::toDto).collect(Collectors.toList());
    }

    public void addCoinToWatchlist(int watchlistId, int coinId) {
        watchlistRepository.addCoinToWatchlist(watchlistId, coinId);
    }

    public void deleteCoinFromWatchlist(int watchlistId, int coinId) {
        watchlistRepository.deleteCoinFromWatchlist(watchlistId, coinId);
    }

}
