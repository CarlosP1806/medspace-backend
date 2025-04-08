package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.Product;
import com.medspace.infrastructure.entity.ProductEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity productEntity) {
        Product product = new Product();
        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setId(productEntity.getId());
        product.setUuid(productEntity.getUuid());
        product.setPrice(productEntity.getPrice());
        product.setStock(productEntity.getStock());
        return product;
    }

    public static ProductEntity toEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setId(product.getId());
        productEntity.setUuid(product.getUuid());
        productEntity.setPrice(product.getPrice());
        productEntity.setStock(product.getStock());
        return productEntity;
    }
}
