package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.CoinFullDto;
import ru.investing_portal.mappers.CoinMapper;
import ru.investing_portal.models.domain.Coin;
import ru.investing_portal.repos.CoinRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CoinControllerImpl implements CoinController {

    private final CoinRepository coinRepository;

    private final CoinMapper coinMapper;


    @Override
    public void create(CoinFullDto coinFullDto) {
        coinRepository.save(coinMapper.toCoin(coinFullDto));
    }

    @Override
    public CoinFullDto read(int id) {
        return coinMapper.toDto(coinRepository.findById(id).get());
    }

    @Override
    public void update(int id, CoinFullDto coinFullDto) {
        Coin dbCoin = coinRepository.findById(id).get();
        coinFullDto.setId(id);
        coinMapper.updateCoinFromDto(coinFullDto, dbCoin);
        coinRepository.save(dbCoin);
    }

    @Override
    public void delete(int id) {
        coinRepository.deleteById(id);
    }

    @Override
    public List<CoinFullDto> readAll(Integer pageNum, Integer perPage) {
        if (perPage == null) perPage = 25; // Default page size and number
        if (pageNum == null) pageNum = 0;
        List<Coin> coins = coinRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return coins.stream().map(coinMapper::toDto).collect(Collectors.toList());
    }
}
