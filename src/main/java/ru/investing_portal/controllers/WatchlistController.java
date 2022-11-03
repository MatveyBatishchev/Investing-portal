package ru.investing_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.WatchlistCreateDto;
import ru.investing_portal.dto.WatchlistReadDto;

import java.util.List;


@RequestMapping("/watchlist")
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

}
