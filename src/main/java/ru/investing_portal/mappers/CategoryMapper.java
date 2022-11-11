package ru.investing_portal.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import ru.investing_portal.dto.CategoryDto;
import ru.investing_portal.models.domain.Category;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CategoryMapper {

    @Named("toCategoryDto")
    CategoryDto toDto(Category category);

    @IterableMapping(qualifiedByName = "toCategoryDto")
    List<CategoryDto> map(Collection<Category> categories);

}
