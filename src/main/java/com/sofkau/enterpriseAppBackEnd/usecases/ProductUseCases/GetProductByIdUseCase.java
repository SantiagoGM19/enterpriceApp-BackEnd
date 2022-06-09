package com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProductMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetProductByIdUseCase implements Function<String, Mono<ProductDTO>> {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public Mono<ProductDTO> apply(String id) {
        return productRepository.findById(id).switchIfEmpty(Mono.error(() -> new NoSuchElementException()))
                .onErrorResume(e -> Mono.empty())
                .map(product -> productMapper.convertEntityToDTO().apply(product));
    }
}
