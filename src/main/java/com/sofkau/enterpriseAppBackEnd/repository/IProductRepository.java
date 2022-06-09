package com.sofkau.enterpriseAppBackEnd.repository;

import com.sofkau.enterpriseAppBackEnd.collection.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
}
