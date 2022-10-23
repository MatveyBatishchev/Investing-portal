package ru.investing_portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.investing_portal.models.domain.CurrencyRateRecord;

@FeignClient(value = "currencyRatesClient", url = "${feign.openExchangeRates.api.url}")
public interface CurrencyRatesClient {

    @GetMapping("/latest.json")
    CurrencyRateRecord getCurrencyRates(@RequestParam("app_id") String appId,
                                        @RequestParam("base") String baseCurrencyCode,
                                        @RequestParam("prettyprint") boolean prettyPrint,
                                        @RequestParam("symbols") String currencyCodes);

}
