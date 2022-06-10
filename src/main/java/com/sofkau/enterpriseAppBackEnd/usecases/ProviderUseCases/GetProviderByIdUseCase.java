package com.sofkau.enterpriseAppBackEnd.usecases.ProviderUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProviderMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetProviderByIdUseCase implements Function<String, Mono<ProviderDTO>> {

    private final IProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    @Override
    public Mono<ProviderDTO> apply(String id) {
        return providerRepository.findById(id).switchIfEmpty(Mono.error(new NoSuchElementException()))
                .onErrorResume(e -> Mono.empty())
                .map(provider -> providerMapper.convertEntityToDTO().apply(provider));
    }
}
