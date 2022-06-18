package com.shop.basket.repository;

import com.shop.basket.model.dao.Basket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface BasketRepository extends ElasticsearchRepository<Basket, String> {
    Optional<Basket> findByEmail(String email);
}