package com.sofkau.enterpriseAppBackEnd.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String productId;
    private String name;
    private String description;
    private Integer minimum;
    private Integer maximum;
    private Integer stock;
    private Double price;
    private Provider provider;
}
