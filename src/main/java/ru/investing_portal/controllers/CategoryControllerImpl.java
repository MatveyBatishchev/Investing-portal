package ru.investing_portal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.investing_portal.dto.CategoryDto;
import ru.investing_portal.services.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public List<CategoryDto> readAll(Integer pageNum, Integer perPage) {
        return categoryService.findAllCategories(pageNum, perPage);
    }

    @Override
    public void addCoin(int categoryId, int coinId) {
        categoryService.addCoinToCategory(categoryId, coinId);
    }

    @Override
    public void delete(int categoryId, int coinId) {
        categoryService.deleteCoinFromCategory(categoryId, coinId);
    }

    @Override
    public String tester() {
        return "This server is working!";
    }

}
