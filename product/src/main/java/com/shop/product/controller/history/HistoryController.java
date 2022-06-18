package com.shop.product.controller.history;

import com.shop.common.model.dto.ProductDto;
import com.shop.product.mapper.RevisionMapper;
import com.shop.product.model.dto.CategoryDto;
import com.shop.product.repository.CategoryRepository;
import com.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history/product")
@RequiredArgsConstructor
public class HistoryController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RevisionMapper revisionMapper;

    @GetMapping("/product/{id}")
    public Page<ProductDto> getProductHistory(@PathVariable Long id, @RequestParam int page, @RequestParam int size) {
        return productRepository.findRevisions(id, PageRequest.of(page, size)).map(revisionMapper::toProductDto);
    }

    @GetMapping("/category/{id}")
    public Page<CategoryDto> getCategoryHistory(@PathVariable Long id, @RequestParam int page, @RequestParam int size) {
        return categoryRepository.findRevisions(id, PageRequest.of(page, size)).map(revisionMapper::toCategoryDto);
    }
}

