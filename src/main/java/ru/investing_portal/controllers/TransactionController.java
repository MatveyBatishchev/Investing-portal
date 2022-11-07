package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionGroupDto;
import ru.investing_portal.dto.TransactionReadDto;

import java.util.List;

@Tag(name="Transactions")
@RequestMapping("/transactions")
public interface TransactionController {

    // Transactions
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody TransactionCreateDto transactionCreateDto);

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    TransactionReadDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody TransactionCreateDto transactionCreateDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping(value = "/by-group", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<TransactionReadDto> readByGroup(@RequestParam("group_id") int groupId);

    // Transaction groups
    @GetMapping(value = "/group/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    TransactionGroupDto readGroup(@PathVariable("id") int groupId);

    @DeleteMapping(value = "/group/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteGroup(@PathVariable("id") int groupId);

    @GetMapping(value = "/group/by-portfolio", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<TransactionGroupDto> readGroupByPortfolioId(@RequestParam("portfolio_id") int portfolioId);

}
