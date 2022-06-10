package com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ProviderInterfaces;

import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveProvider {
    Mono<ProviderDTO> apply(ProviderDTO providerDTO);
}
