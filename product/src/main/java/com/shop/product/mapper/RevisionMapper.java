package com.shop.product.mapper;

import com.shop.product.model.dao.Category;
import com.shop.product.model.dao.Product;
import com.shop.product.model.dto.CategoryDto;
import com.shop.product.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.history.Revision;

@Mapper(componentModel = "spring")
public interface RevisionMapper {
    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "entity.price", target = "price")
    @Mapping(source = "entity.description", target = "description")
    @Mapping(source = "entity.quantity", target = "quantity")
    @Mapping(source = "requiredRevisionNumber", target = "revisionNumber")
    ProductDto toProductDto(Revision<Integer, Product> productRevision);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "entity.description", target = "description")
    @Mapping(source = "requiredRevisionNumber", target = "revisionNumber")
    CategoryDto toCategoryDto(Revision<Integer, Category> categoryRevision);
}