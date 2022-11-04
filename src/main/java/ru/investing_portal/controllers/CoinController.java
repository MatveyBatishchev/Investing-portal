package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.CoinFullDto;

import java.util.List;

@Tag(name="Coins")
@RequestMapping("/coins")
public interface CoinController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody CoinFullDto coinFullDto);

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    CoinFullDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody CoinFullDto coinFullDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<CoinFullDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                              @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);


    @Operation(summary = "Find all coins associated with exact category")
    @GetMapping(value = "/by-category", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<CoinFullDto> readByCategoryId(@RequestParam("category_id") int categoryId,
                                       @RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                                       @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

}
