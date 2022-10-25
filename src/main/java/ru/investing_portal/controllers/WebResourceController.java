package ru.investing_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.WebResourceDto;

import java.util.List;

@RequestMapping("/web_resource")
public interface WebResourceController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody WebResourceDto webResourceDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    WebResourceDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody WebResourceDto webResourceDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    List<WebResourceDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                                 @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

}