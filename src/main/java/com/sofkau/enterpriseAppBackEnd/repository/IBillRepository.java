package com.sofkau.enterpriseAppBackEnd.repository;

import com.sofkau.enterpriseAppBackEnd.collection.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBillRepository extends ReactiveMongoRepository<Bill, String> {
}
