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
    public void create(CoinFullDto coinFullDto) {
        coinService.createCoin(coinFullDto);
    }

    @Override
    public CoinFullDto read(int id) {
        return coinService.findCoinById(id);
    }

    @Override
    public void update(int id, CoinFullDto coinFullDto) {
        coinService.updateCoin(id, coinFullDto);
    }

    @Override
    public void delete(int id) {
        coinService.deleteCoinById(id);
    }

    @Override
    public List<CoinFullDto> readAll(Integer pageNum, Integer perPage) {
        return coinService.findAllCoins(pageNum, perPage);
    }
}
