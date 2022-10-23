package ru.investing_portal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.TransactionDto;

import java.util.List;

@RequestMapping("/transaction")
public interface TransactionController {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody TransactionDto transactionDto);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TransactionDto read(@PathVariable("id") int id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@PathVariable("id") int id, @RequestBody TransactionDto transactionDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int id);

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    List<TransactionDto> readAll(@RequestParam(value = "page", required = false) Integer pageNum,
                                 @RequestParam(value = "per_page", required = false) Integer perPage);

}
