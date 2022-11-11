package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.models.domain.FiatCurrency;
import ru.investing_portal.services.FiatCurrencyService;

import java.util.List;

@RestController
@AllArgsConstructor
public class FiatCurrencyControllerImpl implements FiatCurrencyController {

    private final FiatCurrencyService fiatCurrencyService;

    @Override
    public FiatCurrency read(int id) {
        return fiatCurrencyService.findFiatCurrencyById(id);
    }

    @Override
    public FiatCurrency readByCode(String code) {
        return fiatCurrencyService.findFiatCurrencyByCode(code);
    }

    @Override
    public List<FiatCurrency> readAll(Integer pageNum, Integer perPage) {
        return fiatCurrencyService.findAllFiatCurrencies(pageNum, perPage);
    }

}
