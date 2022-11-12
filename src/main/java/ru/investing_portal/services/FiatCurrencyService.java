package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.models.domain.FiatCurrency;
import ru.investing_portal.repos.FiatCurrencyRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FiatCurrencyService {

    private final FiatCurrencyRepository fiatCurrencyRepository;

    public FiatCurrency findFiatCurrencyById(int id) {
        return fiatCurrencyRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Фиатная валюта с id " + id + " не была найдена!"));
    }

    public FiatCurrency findFiatCurrencyByCode(String code) {
        return fiatCurrencyRepository.findFiatCurrencyByCode(code);
    }

    public List<FiatCurrency> findAllFiatCurrencies(Integer pageNum, Integer perPage) {
        return fiatCurrencyRepository.findAll(PageRequest.of(pageNum, perPage)).getContent();
    }
}
