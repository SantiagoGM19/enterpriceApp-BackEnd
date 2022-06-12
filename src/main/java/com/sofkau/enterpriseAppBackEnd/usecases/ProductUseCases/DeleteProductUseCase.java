package com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases;

import com.sofkau.enterpriseAppBackEnd.mapper.ProductMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProductRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ProductInterfaces.DeleteProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DeleteProductUseCase implements DeleteProduct {

    private final IProductRepository productRepository;

    @Override
    public Mono<Void> apply(String id) {

        return productRepository.findById(id)
                .flatMap(product -> productRepository.deleteById(id));
    }
}
