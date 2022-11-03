package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.WatchlistCreateDto;
import ru.investing_portal.dto.WatchlistReadDto;
import ru.investing_portal.services.WatchlistService;

import java.util.List;

@RestController
@AllArgsConstructor
public class WatchlistControllerImpl implements WatchlistController {

    private final WatchlistService watchlistService;

    @Override
    public void create(WatchlistCreateDto watchlistCreateDto) {
        watchlistService.createWatchlist(watchlistCreateDto);
    }

    @Override
    public WatchlistReadDto read(int id) {
        return watchlistService.findWatchlistById(id);
    }

    @Override
    public void update(int id, WatchlistCreateDto watchlistCreateDto) {
        watchlistService.updateWatchlist(id, watchlistCreateDto);
    }

    @Override
    public void delete(int id) {
        watchlistService.deleteWatchlistById(id);
    }

    @Override
    public List<WatchlistReadDto> readAll(Integer pageNum, Integer perPage) {
       return watchlistService.findAllWatchlists(pageNum, perPage);
    }

}