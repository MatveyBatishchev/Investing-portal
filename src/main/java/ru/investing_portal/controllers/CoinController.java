package ru.investing_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.CoinFullDto;

import java.util.List;

@RequestMapping("/coin")
public interface CoinController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody CoinFullDto coinFullDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CoinFullDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody CoinFullDto coinFullDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    List<CoinFullDto> readAll(@RequestParam(value = "page", required = false) Integer pageNum,
                              @RequestParam(value = "per_page", required = false) Integer perPage);

}
