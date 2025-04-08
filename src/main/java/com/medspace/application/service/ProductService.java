package com.medspace.application.service;

import com.medspace.domain.model.Product;
import com.medspace.domain.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public Product createProduct(Product product) {
        product.setUuid(UUID.randomUUID().toString());
        product = productRepository.insertProduct(product);
        return product;
    }
}
