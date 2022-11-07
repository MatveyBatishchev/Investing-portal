package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.WatchlistCreateDto;
import ru.investing_portal.dto.WatchlistReadDto;

import java.util.List;

@Tag(name = "Watchlists")
@RequestMapping("/watchlists")
public interface WatchlistController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody WatchlistCreateDto watchlistCreateDto);

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    WatchlistReadDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody WatchlistCreateDto watchlistCreateDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<WatchlistReadDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                                   @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

    @Operation(summary = "Add coin to user watchlist")
    @PostMapping("/{id}/add-coin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCoin(@PathVariable("id") int watchlistId, @RequestParam("coin_id") int coinId);

    @Operation(summary = "Delete coin from user watchlist")
    @DeleteMapping("/{id}/delete-coin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int watchlistId, @RequestParam("coin_id") int coinId);

}
