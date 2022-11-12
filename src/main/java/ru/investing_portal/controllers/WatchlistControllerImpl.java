package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.WatchlistDto;
import ru.investing_portal.services.WatchlistService;

import java.util.List;

@RestController
@AllArgsConstructor
public class WatchlistControllerImpl implements WatchlistController {

    private final WatchlistService watchlistService;

    @Override
    public void create(WatchlistDto watchlistDto) {
        watchlistService.createWatchlist(watchlistDto);
    }

    @Override
    public WatchlistDto read(int id) {
        return watchlistService.findWatchlistById(id);
    }

    @Override
    public void update(int id, WatchlistDto watchlistDto) {
        watchlistService.updateWatchlist(id, watchlistDto);
    }

    @Override
    public void delete(int id) {
        watchlistService.deleteWatchlistById(id);
    }

    @Override
    public List<WatchlistDto> readAll(Integer pageNum, Integer perPage) {
       return watchlistService.findAllWatchlists(pageNum, perPage);
    }

    @Override
    public void addCoin(int watchlistId, int coinId) {
        watchlistService.addCoinToWatchlist(watchlistId, coinId);
    }

    @Override
    public void delete(int watchlistId, int coinId) {
        watchlistService.deleteCoinFromWatchlist(watchlistId, coinId);
    }

}