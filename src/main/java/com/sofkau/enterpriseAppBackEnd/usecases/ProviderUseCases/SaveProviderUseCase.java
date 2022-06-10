package com.sofkau.enterpriseAppBackEnd.usecases.ProviderUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProviderMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProviderRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ProviderInterfaces.SaveProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveProviderUseCase implements SaveProvider {

    private final IProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    @Override
    public Mono<ProviderDTO> apply(ProviderDTO providerDTO) {
        return providerRepository.save(providerMapper.convertDTOToEntity().apply(providerDTO))
                .map(provider -> providerMapper.convertEntityToDTO().apply(provider));
    }
}
