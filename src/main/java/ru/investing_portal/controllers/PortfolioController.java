package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.PortfolioFullDto;
import ru.investing_portal.dto.PortfolioShortDto;

import java.util.List;

@Tag(name="Portfolios")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/portfolios")
public interface PortfolioController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody PortfolioShortDto portfolioShortDto);

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    PortfolioFullDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody PortfolioShortDto portfolioShortDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping(value = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<PortfolioShortDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                                    @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

}
