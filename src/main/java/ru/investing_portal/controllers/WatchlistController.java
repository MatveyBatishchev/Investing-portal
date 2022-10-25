package ru.investing_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.WatchlistDto;

import java.util.List;


@RequestMapping("/watchlist")
public interface WatchlistController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody WatchlistDto watchlistDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    WatchlistDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody WatchlistDto watchlistDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    List<WatchlistDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                               @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

}
