package com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases;

import com.sofkau.enterpriseAppBackEnd.collection.Product;
import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProductMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllProductsUseCase implements Supplier<Flux<ProductDTO>> {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Flux<ProductDTO> get() {
        return productRepository.findAll().map(product -> productMapper.convertEntityToDTO().apply(product));
    }
}
