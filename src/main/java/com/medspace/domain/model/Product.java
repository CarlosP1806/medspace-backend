package com.medspace.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String name;
    private String description;
    private int id;
    private String uuid;
    private float price;
    private Integer stock;

    public Product(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
