package com.medspace.application.usecase;

import com.medspace.application.service.ProductService;
import com.medspace.domain.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateProductUseCase {

    @Inject
    ProductService productService;

    public Product execute(Product product) {
        product = productService.createProduct(product);
        return product;
    }
}
