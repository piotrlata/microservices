package com.shop.basket.mapper;

import com.shop.basket.model.dao.BasketProduct;
import com.shop.common.model.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    BasketProduct dtoToDao(ProductDto productDto);

    List<ProductDto> listDaoToListDto(List<BasketProduct> basketProducts);
}
