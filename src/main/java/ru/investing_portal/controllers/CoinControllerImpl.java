package ru.investing_portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.CoinFullDto;
import ru.investing_portal.services.CoinService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoinControllerImpl implements CoinController {

    private final CoinService coinService;

    @Override
    public CoinFullDto read(int id) {
        return coinService.findCoinById(id);
    }

    @Override
    public List<CoinFullDto> readAll(Integer pageNum, Integer perPage) {
        return coinService.findAllCoins(pageNum, perPage);
    }

    @Override
    public List<CoinFullDto> readByCategoryId(int categoryId, Integer pageNum, Integer perPage) {
        return coinService.findByCategoryId(categoryId, pageNum, perPage);
    }

    @Override
    public List<CoinFullDto> readByWatchlistId(int watchlistId) {
        return null;
    }

}
