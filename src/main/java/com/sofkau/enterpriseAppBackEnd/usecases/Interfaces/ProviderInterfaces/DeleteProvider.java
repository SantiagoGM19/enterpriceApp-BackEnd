package com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ProviderInterfaces;


import reactor.core.publisher.Mono;

@FunctionalInterface
public interface DeleteProvider {
    Mono<Void> apply(String id);
}
