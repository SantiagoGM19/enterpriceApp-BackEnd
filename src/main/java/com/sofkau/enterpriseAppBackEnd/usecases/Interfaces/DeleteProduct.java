package com.sofkau.enterpriseAppBackEnd.usecases.Interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteProduct {
    Mono<Void> apply(String id);
}
