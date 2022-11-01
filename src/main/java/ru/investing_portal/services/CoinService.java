package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.CoinFullDto;
import ru.investing_portal.mappers.CoinMapper;
import ru.investing_portal.models.domain.Coin;
import ru.investing_portal.repos.CoinRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinService {

    private final CoinRepository coinRepository;

    private final CoinMapper coinMapper;

    private Coin getCoinById(int id) {
        return coinRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Монета с id " + id + " не была найдена!"));
    }

    public void createCoin(CoinFullDto coinFullDto) {
        coinRepository.save(coinMapper.toCoin(coinFullDto));
    }

    public CoinFullDto findCoinById(int id) {
        return coinMapper.toFullDto(getCoinById(id));
    }

    public void updateCoin(int id, CoinFullDto coinFullDto) {
        Coin dbCoin = getCoinById(id);
        coinFullDto.setId(id);
        coinMapper.updateCoinFromDto(coinFullDto, dbCoin);
        coinRepository.save(dbCoin);
    }

    public void deleteCoinById(int id) {
        coinRepository.deleteById(id);
    }

    public List<CoinFullDto> findAllCoins(Integer pageNum, Integer perPage) {
        List<Coin> coins = coinRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
        return coins.stream().map(coinMapper::toFullDto).collect(Collectors.toList());
    }

}
