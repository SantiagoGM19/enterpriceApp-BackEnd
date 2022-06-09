package com.sofkau.enterpriseAppBackEnd.dto;

import com.sofkau.enterpriseAppBackEnd.collection.Provider;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTO {
    private String productId = UUID.randomUUID().toString().substring(0, 10);
    private String name;
    private String description;
    private Integer minimum;
    private Integer maximum;
    private Integer stock;
    private Double price;
    private Provider provider;
}
