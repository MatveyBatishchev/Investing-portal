package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.CoinFullDto;
import ru.investing_portal.feign.CoinGekoClient;
import ru.investing_portal.mappers.CoinMapper;
import ru.investing_portal.models.domain.Coin;
import ru.investing_portal.repos.CoinRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinService {

    private final CoinRepository coinRepository;

    private final CoinGekoClient coinGekoClient;

    private final CoinMapper coinMapper;

    @Value("${project.base-currency}")
    private String baseCurrency;

    @Value("${feign.coingeko.price_change_percentage}")
    private String priceChangePercentage;


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
        return coinMapper.map(coins);
    }

    public List<CoinFullDto> findByCategoryId(int categoryId, Integer pageNum, Integer perPage) {
        return coinMapper.map(coinRepository.findCoinsByCategoriesId(categoryId, PageRequest.of(pageNum, perPage)));
    }

    public void test() {
        List<Coin> coins = coinGekoClient.getCoinMarketsData(baseCurrency, priceChangePercentage);
        coinRepository.saveAll(coins);
    }

}
