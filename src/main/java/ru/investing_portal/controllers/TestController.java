package ru.investing_portal.controllers;

import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.feign.CoinGekoClient;
import ru.investing_portal.feign.CurrencyRatesClient;
import ru.investing_portal.models.domain.CurrencyRateRecord;
import ru.investing_portal.models.domain.FiatCurrency;
import ru.investing_portal.repos.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor=@__({@Autowired}))
@RequestMapping("/test")
public class TestController {

    @Value("${project.base-currency}")
    private String baseCurrency;

    @Value("${feign.coingeko.price_change_percentage}")
    private String priceChangePercentage;

    @Value("${feign.openExchangeRates.api.key}")
    private String currencyRatesApiCode;

    private final CoinGekoClient coinGekoClient;

    private final CurrencyRatesClient currencyRatesClient;

    private final CoinRepository coinRepository;

    private final WebResourceRepository webResourceRepository;

    private final PortfolioRepository portfolioRepository;

    private final FiatCurrencyRepository fiatCurrencyRepository;

    private final TransactionRepository transactionRepository;

    private final CategoryRepository categoryRepository;

    private final WatchlistRepository watchlistRepository;


    @GetMapping()
    public void runTest() {
        CurrencyRateRecord currencyRateRecord = currencyRatesClient.getCurrencyRates(currencyRatesApiCode,
                baseCurrency, true, "USD,AUD,BRL,CAD,CHF,CLP,CNY,CZK," +
                        "DKK,EUR,GBP,HKD,HUF,IDR,ILS,INR,JPY,KRW,MXN,MYR,NOK,NZD," +
                        "PHP,PKR,PLN,RUB,SEK,SGD,THB,TRY,TWD,ZAR,VND,MAD,IRR,ARS,RON,UAH,NGN,AED,COP,EGP,SAR,BDT,GHS,BGN,VES");
        for (Map.Entry<String, Double> pair : currencyRateRecord.getRates().entrySet()) {
            FiatCurrency fiatCurrency = new FiatCurrency();
            fiatCurrency.setSymbol(pair.getKey());
            fiatCurrency.setRate(pair.getValue());
            fiatCurrency.setLastUpdated(DateTime.now());
            fiatCurrencyRepository.save(fiatCurrency);
        }
//        List<Coin> coins = coinGekoClient.getCoinMarketsData(baseCurrency, priceChangePercentage);
//        coinRepository.saveAll(coins);
    }

}