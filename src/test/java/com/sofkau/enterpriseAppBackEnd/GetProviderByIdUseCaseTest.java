package com.sofkau.enterpriseAppBackEnd;
import com.sofkau.enterpriseAppBackEnd.collection.Provider;
import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProviderMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProviderRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.ProviderUseCases.GetProviderByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class GetProviderByIdUseCaseTest {
    private GetProviderByIdUseCase getProviderByIdUseCase;

    @Autowired
    private ProviderMapper providerMapper;

    @Mock
    IProviderRepository providerRepository;

    @BeforeEach
    void setUp(){
        getProviderByIdUseCase = new GetProviderByIdUseCase(providerRepository, providerMapper);
    }

    @Test
    public void getProviderByIdTest(){
        Provider provider = new Provider("pr-12","products S.A.S", "3145612903");

        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setProviderId(provider.getProviderId());
        providerDTO.setName(provider.getName());
        providerDTO.setPhone(provider.getPhone());

        Mockito.when(providerRepository.findById(provider.getProviderId())).thenReturn(Mono.just(provider));

        Mono<ProviderDTO> publisher = getProviderByIdUseCase.apply(provider.getProviderId());

        StepVerifier
                .create(publisher)
                .expectNext(providerDTO)
                .verifyComplete();

        Mockito.verify(providerRepository).findById(provider.getProviderId());
    }
}
