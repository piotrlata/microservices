package com.shop.product.service.impl;

import com.shop.product.model.dao.Product;
import com.shop.product.repository.ProductRepository;
import com.shop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Product update(Product product, Long id) {
        var productDb = findProductById(id);
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setDescription(product.getDescription());
        productDb.setQuantity(product.getQuantity());
        return productDb;
    }

    @Override
    public Product findProductById(Long id) {
        log.info("object with id {} not in cache", id);
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
