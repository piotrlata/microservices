package com.shop.product.repository;

import com.shop.product.model.dao.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, RevisionRepository<Category, Long, Integer> {
}
