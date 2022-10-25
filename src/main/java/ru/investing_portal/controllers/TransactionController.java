package ru.investing_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.TransactionCreateDto;
import ru.investing_portal.dto.TransactionReadDto;

import java.util.List;

@RequestMapping("/transaction")
public interface TransactionController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody TransactionCreateDto transactionCreateDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TransactionReadDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody TransactionCreateDto transactionCreateDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    List<TransactionReadDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                                       @RequestParam(value = "per_page", defaultValue = "0", required = false) Integer perPage);

}
