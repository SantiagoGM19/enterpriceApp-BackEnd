package com.sofkau.enterpriseAppBackEnd.usecases.ProviderUseCases;

import com.sofkau.enterpriseAppBackEnd.repository.IProductRepository;
import com.sofkau.enterpriseAppBackEnd.repository.IProviderRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ProviderInterfaces.DeleteProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DeleteProviderUseCase implements DeleteProvider {

    private final IProviderRepository providerRepository;

    @Override
    public Mono<Void> apply(String id) {
        return providerRepository.findById(id)
                .flatMap(provider -> providerRepository.deleteById(id))
                .switchIfEmpty(Mono.error(new NoSuchElementException()));
    }
}
