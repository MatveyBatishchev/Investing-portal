package ru.investing_portal.mappers;

import org.mapstruct.Mapper;
import ru.investing_portal.dto.CategoryDto;
import ru.investing_portal.models.domain.Category;

import java.util.List;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    List<CategoryDto> map(List<Category> categories);

}
