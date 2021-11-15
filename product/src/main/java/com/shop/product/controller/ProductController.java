package com.shop.product.controller;

import com.shop.product.mapper.ProductMapper;
import com.shop.product.model.dto.ProductDto;
import com.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    public ProductDto productById(@PathVariable Long id) {
        return productMapper.daoToDto(productService.findProductById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto saveProduct(@RequestBody @Valid ProductDto product) {
        return productMapper.daoToDto(productService.save(productMapper.dtoToDao(product)));
    }

    @GetMapping
    public Page<ProductDto> productPage(@RequestParam int page, @RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size)).map(productMapper::daoToDto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto updateProduct(@RequestBody @Valid ProductDto product, @PathVariable Long id) {
        return productMapper.daoToDto(productService.update(productMapper.dtoToDao(product), id));
    }
}
