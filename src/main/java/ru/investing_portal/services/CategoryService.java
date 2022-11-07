package ru.investing_portal.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.investing_portal.dto.CategoryDto;
import ru.investing_portal.mappers.CategoryMapper;
import ru.investing_portal.repos.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAllCategories(Integer pageNum, Integer perPage) {
        return categoryMapper.map(categoryRepository.findAll(PageRequest.of(pageNum, perPage)).getContent());
    }

    public void addCoinToCategory(int categoryId, int coinId) {
        categoryRepository.addCoinToCategory(categoryId, coinId);
    }

    public void deleteCoinFromCategory(int categoryId, int coinId) {
        categoryRepository.deleteCoinFromCategory(categoryId, coinId);
    }

}
