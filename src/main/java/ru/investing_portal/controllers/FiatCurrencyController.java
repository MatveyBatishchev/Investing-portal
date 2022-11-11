package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.models.domain.FiatCurrency;

import java.util.List;

@Tag(name="Fiat-currencies")
@RequestMapping("/fiat_currencies")
public interface FiatCurrencyController {

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    FiatCurrency read(@PathVariable("id") int id);

    @Operation(summary = "Найти фиатную валюту по коду ISO-4217")
    @GetMapping(value = "/by-code", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    FiatCurrency readByCode(@RequestParam("currency_code") String code);

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<FiatCurrency> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                               @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

}
