package com.shop.basket.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketProduct {
    private Long id;
    private String name;
    private Integer quantity;
    private Double price;
}
