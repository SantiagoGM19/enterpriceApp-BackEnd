package com.sofkau.enterpriseAppBackEnd.usecases.ProviderUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProviderMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllProvidersUseCase implements Supplier<Flux<ProviderDTO>> {

    private final IProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    @Override
    public Flux<ProviderDTO> get() {
        return providerRepository.findAll().map(provider -> providerMapper.convertEntityToDTO().apply(provider));
    }
}
