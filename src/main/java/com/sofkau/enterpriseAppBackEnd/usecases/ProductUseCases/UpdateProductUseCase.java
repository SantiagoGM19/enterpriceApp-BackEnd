package com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ProductMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IProductRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.UpdateProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UpdateProductUseCase implements UpdateProduct {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<ProductDTO> apply(String id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    productDTO.setProductId(id);
                    return productRepository.save(productMapper.convertDTOToEntity().apply(productDTO))
                            .map(productResponse -> productMapper.convertEntityToDTO().apply(productResponse));
                }).switchIfEmpty(Mono.just(new ProductDTO()));
    }
}
