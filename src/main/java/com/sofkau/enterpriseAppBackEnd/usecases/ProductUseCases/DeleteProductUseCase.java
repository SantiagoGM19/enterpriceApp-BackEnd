package com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProductMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProductRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.DeleteProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DeleteProductUseCase implements DeleteProduct {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<Void> apply(String id) {

        return productRepository.findById(id)
                .flatMap(product -> productRepository.deleteById(id))
                .switchIfEmpty(Mono.error(new NoSuchElementException()));
    }
}
