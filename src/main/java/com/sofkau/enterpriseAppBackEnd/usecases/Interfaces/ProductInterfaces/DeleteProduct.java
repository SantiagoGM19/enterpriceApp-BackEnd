package com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ProductInterfaces;

import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteProduct {
    Mono<Void> apply(String id);
}
