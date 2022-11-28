package ru.investing_portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.investing_portal.dto.CoinRate;

import java.util.Map;

@FeignClient(value = "cryptoCompareClient", url = "${feign.cryptocompare.api.url}")
public interface CryptoCompareClient {

    @GetMapping("/data/pricemulti")
    ResponseEntity<Map<String, CoinRate>> getCoinMarketsData(@RequestParam("api_key") String apiKey,
                                                             @RequestParam("fsyms") String coinSymbols,
                                                             @RequestParam("tsyms") String baseCurrency);

}
