package ru.investing_portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.feign.CoinGekoClient;
import ru.investing_portal.repos.*;

@RestController
@RequiredArgsConstructor(onConstructor=@__({@Autowired}))
@RequestMapping("/test")
public class TestController {

    @Value("${project.base-currency}")
    private String baseCurrency;

    private final CoinGekoClient coinGekoClient;

    private final CoinRepository coinRepository;

    private final WebResourceRepository webResourceRepository;

    private final PortfolioRepository portfolioRepository;

    private final FiatCurrencyRepository fiatCurrencyRepository;

    private final TransactionRepository transactionRepository;

    private final CategoryRepository categoryRepository;

    private final WatchlistRepository watchlistRepository;


    @GetMapping()
    public void runTest()  {
        watchlistRepository.deleteById(1);
    }

}