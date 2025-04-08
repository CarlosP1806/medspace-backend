package com.medspace.infrastructure.repository;

import com.medspace.domain.model.Product;
import com.medspace.domain.repository.ProductRepository;
import com.medspace.infrastructure.entity.ProductEntity;
import com.medspace.infrastructure.mapper.ProductMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository, PanacheRepositoryBase<ProductEntity, Integer> {

    @Override
    @Transactional
    public Product insertProduct(Product product) {
        ProductEntity productEntity = ProductMapper.toEntity(product);
        persist(productEntity);
        product = ProductMapper.toDomain(productEntity);
        return product;
    }

}
