package ru.investing_portal.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.models.domain.FiatCurrency;
import ru.investing_portal.repos.FiatCurrencyRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class FiatCurrencyControllerImpl implements FiatCurrencyController {

    private final FiatCurrencyRepository fiatCurrencyRepository;

    @Override
    public FiatCurrency read(int id) {
        return fiatCurrencyRepository.findById(id).get();
    }

    @Override
    public List<FiatCurrency> readAll(Integer pageNum, Integer perPage) {
        return fiatCurrencyRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
    }

}
