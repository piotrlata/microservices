package com.shop.basket.service.impl;

import com.shop.basket.client.ProductClient;
import com.shop.basket.client.UserClient;
import com.shop.basket.mapper.ProductMapper;
import com.shop.basket.model.dao.Basket;
import com.shop.basket.model.dao.BasketProduct;
import com.shop.basket.repository.BasketRepository;
import com.shop.basket.service.BasketService;
import com.shop.common.model.dto.ProductDto;
import com.shop.common.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final ProductMapper productMapper;
    private final ProductClient productClient;
    private final UserClient userClient;

    @Override
    public void addProduct(Long id, Integer quantity) {
        UserDto currentUser = userClient.getCurrentUser();
        ProductDto product = productClient.productById(id);
        Basket basket = basketRepository.findByEmail(currentUser.getEmail()).orElseGet(() -> Basket.builder()
                .email(currentUser.getEmail())
                .products(new LinkedList<>())
                .build());
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("not enough quantity");
        }
        BasketProduct basketProduct = productMapper.dtoToDao(product);
        basketProduct.setQuantity(quantity);
        basket.getProducts().add(basketProduct);
        basketRepository.save(basket);
    }

    @Override
    public void removeProduct(Long id) {
        UserDto currentUser = userClient.getCurrentUser();
        Optional<Basket> optionalBasket = basketRepository.findByEmail(currentUser.getEmail());
        if (optionalBasket.isEmpty()) {
            throw new IllegalArgumentException("no basket");
        }
        Basket basket = optionalBasket.get();
        List<BasketProduct> products = basket.getProducts();
        products.removeIf(product -> product.getId().equals(id));
        basket.setProducts(products);
        basketRepository.save(basket);
    }

    @Override
    public void clearBasket() {
        UserDto currentUser = userClient.getCurrentUser();
        Optional<Basket> optionalBasket = basketRepository.findByEmail(currentUser.getEmail());
        if (optionalBasket.isEmpty()) {
            throw new IllegalArgumentException("no basket");
        }
        Basket basket = optionalBasket.get();
        basket.getProducts().clear();
        basketRepository.save(basket);
    }

    @Override
    public void updateProduct(Long id, Integer quantity) {
        UserDto currentUser = userClient.getCurrentUser();
        ProductDto product = productClient.productById(id);
        Basket basket = basketRepository.findByEmail(currentUser.getEmail()).orElseThrow(() -> new IllegalArgumentException("no basket"));
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("not enough quantity");
        }

    }

    @Override
    public List<BasketProduct> getProducts() {
        UserDto currentUser = userClient.getCurrentUser();
        Optional<Basket> optionalBasket = basketRepository.findByEmail(currentUser.getEmail());
        if (optionalBasket.isEmpty()) {
            throw new IllegalArgumentException("no basket");
        }
        return optionalBasket.get().getProducts();
    }
}
