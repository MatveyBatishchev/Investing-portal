package ru.investing_portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.feign.CoinGekoClient;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${project.base-currency}")
    private String baseCurrency;

    private final CoinGekoClient coinGekoClient;

    @Autowired
    public TestController(CoinGekoClient coinGekoClient) {
        this.coinGekoClient = coinGekoClient;
    }

    @GetMapping()
    public void runTest() {
        System.out.println(coinGekoClient.getCoinMarketsData(baseCurrency).get(0).getLastUpdated().dayOfWeek().get());
    }

}