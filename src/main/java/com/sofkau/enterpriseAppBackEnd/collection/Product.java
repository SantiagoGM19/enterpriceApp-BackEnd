package com.sofkau.enterpriseAppBackEnd.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
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
