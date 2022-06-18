package com.shop.basket.controller;

import com.shop.basket.mapper.ProductMapper;
import com.shop.basket.model.dto.BasketDto;
import com.shop.basket.service.BasketService;
import com.shop.common.model.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/baskets")
@PreAuthorize("isAuthenticated()")
public class BasketController {
    private final BasketService basketService;
    private final ProductMapper productMapper;

    @PostMapping
    public void addProduct(@RequestBody BasketDto basketDto) {
        basketService.addProduct(basketDto.getProductId(), basketDto.getQuantity());
    }

    @DeleteMapping
    public void removeProduct(@RequestParam Long productId) {
        basketService.removeProduct(productId);
    }

    @DeleteMapping("/clear")
    private void clearBasket() {
        basketService.clearBasket();
    }

    @PutMapping
    private void updateProduct(@RequestBody BasketDto basketDto) {
        basketService.updateProduct(basketDto.getProductId(), basketDto.getQuantity());
    }

    @GetMapping
    private List<ProductDto> getProducts() {
        return productMapper.listDaoToListDto(basketService.getProducts());
    }
}
