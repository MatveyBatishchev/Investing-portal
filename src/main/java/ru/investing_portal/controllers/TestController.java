package ru.investing_portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.feign.CoinGekoClient;
import ru.investing_portal.models.domain.Coin;
import ru.investing_portal.repos.CoinRepository;
import ru.investing_portal.repos.PortfolioRepository;
import ru.investing_portal.repos.WebResourceRepository;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${project.base-currency}")
    private String baseCurrency;

    private final CoinGekoClient coinGekoClient;

    private final CoinRepository coinRepository;

    private final WebResourceRepository webResourceRepository;

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public TestController(CoinGekoClient coinGekoClient, CoinRepository coinRepository,
                          WebResourceRepository webResourceRepository, PortfolioRepository portfolioRepository) {
        this.coinGekoClient = coinGekoClient;
        this.coinRepository = coinRepository;
        this.webResourceRepository = webResourceRepository;
        this.portfolioRepository = portfolioRepository;
    }

    @GetMapping()
    public void runTest() {
        List<Coin> coins = coinGekoClient.getCoinMarketsData(baseCurrency);
        coinRepository.saveAll(coins);
    }

}