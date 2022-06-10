package com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ProviderInterfaces;

import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UpdateProvider {
    Mono<ProviderDTO> apply(String id, ProviderDTO providerDTO);
}
