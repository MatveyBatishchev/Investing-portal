package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.CategoryDto;

import java.util.List;

@Tag(name="Categories")
@RequestMapping("/categories")
public interface CategoryController {

    @Operation(summary = "Get list of all categories")
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    List<CategoryDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                              @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

    @Operation(summary = "Add coin to category")
    @PostMapping("/{id}/add-coin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCoin(@PathVariable("id") int categoryId, @RequestParam("coin_id") int coinId);

    @Operation(summary = "Delete coin from category")
    @DeleteMapping("/{id}/delete-coin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int categoryId, @RequestParam("coin_id") int coinId);

    // Задел на то, что дальше категории будут содержать подробную информацию

}
