package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.WatchlistDto;
import ru.investing_portal.mappers.WatchlistMapper;
import ru.investing_portal.models.domain.Watchlist;
import ru.investing_portal.repos.WatchlistRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class WatchlistControllerImpl implements WatchlistController {

    private final WatchlistRepository watchlistRepository;

    private final WatchlistMapper watchlistMapper;

    @Override
    public void create(WatchlistDto watchlistDto) {
        watchlistRepository.save(watchlistMapper.toWatchlist(watchlistDto));
    }

    @Override
    public WatchlistDto read(int id) {
        return watchlistMapper.toDto(watchlistRepository.findById(id).get());
    }

    @Override
    public void update(int id, WatchlistDto watchlistDto) {
        Watchlist dbWatchlist = watchlistRepository.findById(id).get();
        watchlistDto.setId(id);
        watchlistMapper.updateWatchlistFromDto(watchlistDto, dbWatchlist);
        watchlistRepository.save(dbWatchlist);
    }

    @Override
    public void delete(int id) {
        watchlistRepository.deleteById(id);
    }

    @Override
    public List<WatchlistDto> readAll(Integer pageNum, Integer perPage) {
        List<Watchlist> portfolios = watchlistRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return portfolios.stream().map(watchlistMapper::toDto).collect(Collectors.toList());
    }

}