package com.sofkau.enterpriseAppBackEnd;

import com.sofkau.enterpriseAppBackEnd.collection.Product;
import com.sofkau.enterpriseAppBackEnd.collection.Provider;
import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProductMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProductRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases.GetProductByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class GetProductByIdUseCaseTest {

    private GetProductByIdUseCase getProductByIdUseCase;

    @Autowired
    private ProductMapper productMapper;

    @Mock
    IProductRepository productRepository;

    @BeforeEach
    void setUp(){
        getProductByIdUseCase = new GetProductByIdUseCase(productRepository, productMapper);
    }

    @Test
    public void getProductByIdTest(){
        Product product = new Product("prdrs-2","videoCard","rtx 3000", 5, 40, 20, 2000.0, null);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setMinimum(product.getMinimum());
        productDTO.setMaximum(product.getMaximum());
        productDTO.setStock(product.getStock());
        productDTO.setPrice(product.getPrice());
        productDTO.setProvider(null);

        Mockito.when(productRepository.findById(product.getProductId())).thenReturn(Mono.just(product));

        Mono<ProductDTO> publisher = getProductByIdUseCase.apply(product.getProductId());

        StepVerifier
                .create(publisher)
                .expectNext(productDTO)
                .verifyComplete();

        Mockito.verify(productRepository).findById(product.getProductId());

    }
}
