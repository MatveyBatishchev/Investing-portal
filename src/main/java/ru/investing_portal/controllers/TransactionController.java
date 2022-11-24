package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionGroupDto;
import ru.investing_portal.dto.TransactionReadDto;

import java.util.List;

@Tag(name="Transactions")
@SecurityRequirement(name = "Bearer Authentication")
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

    @Operation(summary = "Получить все транзакции связанные с конкретной группой транзакций")
    @GetMapping(value = "/by-group", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<TransactionReadDto> readByGroup(@RequestParam("group_id") int groupId);


    // Transaction groups
    @Operation(summary = "Получить группу транзакций по её ID")
    @GetMapping(value = "/group/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    TransactionGroupDto readGroup(@PathVariable("id") int groupId);

    @Operation(summary = "Удалить группу транзакций по её ID")
    @DeleteMapping(value = "/group/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteGroup(@PathVariable("id") int groupId);

    @Operation(summary = "Получить все группы транзакций связанные с конкретным портфолио")
    @GetMapping(value = "/group/by-portfolio", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<TransactionGroupDto> readGroupsByPortfolioId(@RequestParam("portfolio_id") int portfolioId);

}
