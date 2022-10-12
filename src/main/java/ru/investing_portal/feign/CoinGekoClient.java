package ru.investing_portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.investing_portal.models.domain.Coin;

import java.util.List;

@FeignClient(value = "coinGekoClient", url = "${feign.coingeko.api.url}")
public interface CoinGekoClient {

    @GetMapping("/coins/markets")
    List<Coin> getCoinMarketsData(@RequestParam("vs_currency") String currencyCode);

}
