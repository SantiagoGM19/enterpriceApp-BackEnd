package com.sofkau.enterpriseAppBackEnd.usecases.ProviderUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProviderMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProviderRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ProviderInterfaces.UpdateProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UpdateProviderUseCase implements UpdateProvider {

    private final IProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    @Override
    public Mono<ProviderDTO> apply(String id, ProviderDTO providerDTO) {
        return providerRepository.findById(id)
                .flatMap(provider -> {
                    providerDTO.setProviderId(id);
                    return providerRepository.save(providerMapper.convertDTOToEntity().apply(providerDTO))
                            .map(providerResponse -> providerMapper.convertEntityToDTO().apply(providerResponse));
                }).switchIfEmpty(Mono.just(new ProviderDTO()));
    }
}
