package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.WatchlistDto;

import java.util.List;

@Tag(name = "Watchlists")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/watchlists")
public interface WatchlistController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody WatchlistDto watchlistDto);

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    WatchlistDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody WatchlistDto watchlistDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<WatchlistDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                               @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

    @Operation(summary = "Добавить монету в watchlist пользователя")
    @PostMapping("/{id}/add-coin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCoin(@PathVariable("id") int watchlistId, @RequestParam("coin_id") int coinId);

    @Operation(summary = "Удалить монету из watchlist-а пользователя")
    @DeleteMapping("/{id}/delete-coin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int watchlistId, @RequestParam("coin_id") int coinId);

}
