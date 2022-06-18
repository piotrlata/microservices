package com.shop.basket.service;

import com.shop.basket.model.dao.BasketProduct;

import java.util.List;

public interface BasketService {
    void addProduct(Long id, Integer quantity);

    void removeProduct(Long id);

    void clearBasket();

    void updateProduct(Long id, Integer quantity);

    List<BasketProduct> getProducts();
}
