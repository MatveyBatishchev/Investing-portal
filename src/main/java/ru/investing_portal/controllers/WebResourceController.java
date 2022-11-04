package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.WebResourceDto;

import java.util.List;

@Tag(name = "Web-resources")
public interface WebResourceController {

    @PostMapping("/web-resources")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody WebResourceDto webResourceDto);

    @GetMapping(value = "/web-resources/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    WebResourceDto read(@PathVariable("id") int id);

    @PutMapping("/web-resources/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody WebResourceDto webResourceDto);

    @DeleteMapping("/web-resources/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping(value = "/web-resources/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<WebResourceDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                                 @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

    @Operation(summary = "Find all web-resources associated with exact coin")
    @GetMapping(value = "/coins/{coin_id}/web-resources", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<WebResourceDto> readByCoinId(@PathVariable("coin_id") int coinId);

}