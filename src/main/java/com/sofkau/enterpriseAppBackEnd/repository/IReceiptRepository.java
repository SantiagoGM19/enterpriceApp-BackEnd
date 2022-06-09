package com.sofkau.enterpriseAppBackEnd.repository;

import com.sofkau.enterpriseAppBackEnd.collection.Receipt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IReceiptRepository extends ReactiveMongoRepository<Receipt, String> {
}
