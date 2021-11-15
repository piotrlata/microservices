package com.shop.product.controller;

import com.shop.product.mapper.CategoryMapper;
import com.shop.product.model.dto.CategoryDto;
import com.shop.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{id}")
    public CategoryDto categoryById(@PathVariable Long id) {
        return categoryMapper.daoToDto(categoryService.findCategoryById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto saveCategory(@RequestBody CategoryDto category) {
        return categoryMapper.daoToDto(categoryService.save(categoryMapper.dtoToDao(category)));
    }

    @GetMapping
    public Page<CategoryDto> categoryPage(@RequestParam int page, @RequestParam int size) {
        return categoryService.getPage(PageRequest.of(page, size)).map(categoryMapper::daoToDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDto updateCategory(@RequestBody CategoryDto category, @PathVariable Long id) {
        return categoryMapper.daoToDto(categoryService.update(categoryMapper.dtoToDao(category), id));
    }
}
