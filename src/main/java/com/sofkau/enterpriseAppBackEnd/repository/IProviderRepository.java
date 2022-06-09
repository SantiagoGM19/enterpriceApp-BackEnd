package com.sofkau.enterpriseAppBackEnd.repository;

import com.sofkau.enterpriseAppBackEnd.collection.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProviderRepository extends ReactiveMongoRepository<Provider, String> {
}
