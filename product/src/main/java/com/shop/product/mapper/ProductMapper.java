package com.shop.product.mapper;

import com.shop.product.model.dao.Product;
import com.shop.product.model.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product dtoToDao(ProductDto productDto);

    ProductDto daoToDto(Product product);
}