package com.shop.basket.client;

import com.shop.common.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "PRODUCT-SERVICE")
@RequestMapping("/api/products")
public interface ProductClient {
    @GetMapping("/{id}")
    ProductDto productById(@PathVariable Long id);
}
