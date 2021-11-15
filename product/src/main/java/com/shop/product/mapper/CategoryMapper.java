package com.shop.product.mapper;

import com.shop.product.model.dao.Category;
import com.shop.product.model.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToDao(CategoryDto categoryDto);

    CategoryDto daoToDto(Category category);
}
