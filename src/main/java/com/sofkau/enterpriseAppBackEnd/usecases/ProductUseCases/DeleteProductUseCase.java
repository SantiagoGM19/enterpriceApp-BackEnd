package com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases;

import com.sofkau.enterpriseAppBackEnd.repository.IProductRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.DeleteProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteProductUseCase implements DeleteProduct {

    private final IProductRepository productRepository;

    @Override
    public Mono<Void> apply(String id) {
        return productRepository.deleteById(id);
    }
}
