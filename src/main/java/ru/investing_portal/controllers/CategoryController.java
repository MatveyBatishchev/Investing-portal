package ru.investing_portal.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.investing_portal.dto.CategoryDto;

import java.util.List;

@Tag(name="Categories")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/categories")
public interface CategoryController {

    @Operation(summary = "Получить список всех категорий")
    @GetMapping(value = "/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    List<CategoryDto> readAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum,
                              @RequestParam(value = "per_page", defaultValue = "25", required = false) Integer perPage);

    @Operation(summary = "Добавить монету в категорию", deprecated = true)
    @PostMapping("/{id}/add-coin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addCoin(@PathVariable("id") int categoryId, @RequestParam("coin_id") int coinId);

    @Operation(summary = "Удалить монету из категории", deprecated = true)
    @DeleteMapping("/{id}/delete-coin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") int categoryId, @RequestParam("coin_id") int coinId);

    // Задел на то, что дальше категории будут содержать подробную информацию

}
