package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.CoinRate;
import ru.investing_portal.feign.CoinGekoClient;
import ru.investing_portal.feign.CryptoCompareClient;
import ru.investing_portal.feign.CurrencyRatesClient;
import ru.investing_portal.models.domain.Coin;
import ru.investing_portal.models.domain.CurrencyRateRecord;
import ru.investing_portal.repos.CoinRepository;
import ru.investing_portal.repos.FiatCurrencyRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static ru.investing_portal.util.ResourceUtil.asString;

@Service
//@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {

    private final CoinGekoClient coinGekoClient;

    private final CurrencyRatesClient currencyRatesClient;

    private final CryptoCompareClient cryptoCompareClient;

    private final CoinRepository coinRepository;

    private final FiatCurrencyRepository fiatCurrencyRepository;

    @Value("${feign.cryptocompare.api.key}")
    private String cryptoCompareApiKey;

    @Value("${feign.cryptocompare.api.symbols}")
    private Resource cryptoCompareSymbolsResource;

    @Value("${feign.openExchangeRates.api.key}")
    private String ratesClientAppId;

    @Value("${feign.openExchangeRates.api.symbols}")
    private Resource ratesSymbolsResource;

    @Value("${project.base-currency}")
    private String baseCurrency;

    @Value("${feign.coingeko.api.price_change_percentage}")
    private String priceChangePercentage;

    @Value("${feign.coingeko.api.perPage}")
    private Integer perPage;

    @Value("${feign.coingeko.api.pageCount}")
    private Integer pageCount;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // CHECKME: dont configure spring.task.scheduling.pool.size as out tasks are dependent

    private List<Coin> getCoinsMarketData() {
        List<Coin> coins = new ArrayList<>();
        for (int i = 1; i <= pageCount; i++) {
            coins.addAll(coinGekoClient.getCoinMarketsData(baseCurrency, priceChangePercentage, "", perPage, i));
        }
        return coins;
    }

    @Scheduled(fixedDelayString = "${scheduled.delay.coin-main-rates}", initialDelayString = "PT10S")
    public void updateMainCoinsRates() {
        Map<String, CoinRate> mainRates = cryptoCompareClient.getCoinMarketsData(cryptoCompareApiKey, asString(cryptoCompareSymbolsResource), baseCurrency).getBody();
        if (mainRates != null) {
            mainRates.forEach((key, value) -> coinRepository.updateCoinRates(key, value.getCoinRate()));
            log.info("Main coins (50) rates have been updated in {}", dateFormat.format(new Date()));
        }
        else {
            throw new NullPointerException("Main rates from cryptoCompare api in null");
        }
    }

    @Scheduled(fixedDelayString = "${scheduled.delay.coin-all-rates}", initialDelayString = "PT15M")
    public void updateAllCoinsRates() {
        for (Coin coin: getCoinsMarketData()) {
            coinRepository.updateCoinRates(coin.getSymbol(), coin.getCurrentPrice());
        }
        log.info("Coins rates have been updated in {}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelayString = "${scheduled.delay.coin-partial}", initialDelayString = "PT1H")
    public void updateCoinsPartial() {
        for (Coin coin: getCoinsMarketData()) {
            coinRepository.updateCoinPartial(coin.getApiId(), coin.getMarketCap(), coin.getHigh24h(), coin.getLow24h(),
                    coin.getPriceChange24h(), coin.getMarketCapChange24h(), coin.getMarketCapChangePercentage24h(),
                    coin.getChangePercentage1h(), coin.getChangePercentage24h(), coin.getChangePercentage7d());
        }
        log.info("Coins have been partially updated in {}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelayString = "${scheduled.delay.fiat-currency}", initialDelayString = "PT30S")
    public void updateFiatRatesPer10Minutes() {
        CurrencyRateRecord ratesRecord = currencyRatesClient.getCurrencyRates(ratesClientAppId, baseCurrency,
                true, asString(ratesSymbolsResource));
        ratesRecord.getRates().forEach(fiatCurrencyRepository::updateFiatCurrency);
        log.info("Fiat rates have been updated in {}", dateFormat.format(new Date()));
    }

}
