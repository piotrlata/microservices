package com.shop.product.service;

import com.shop.product.model.dao.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Category save(Category category);

    void delete(Long id);

    Category update(Category category, Long id);

    Category findCategoryById(Long id);

    Page<Category> getPage(Pageable pageable);
}
