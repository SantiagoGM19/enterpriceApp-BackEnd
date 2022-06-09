package com.sofkau.enterpriseAppBackEnd.usecases.Interfaces;

import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import reactor.core.publisher.Mono;

 @FunctionalInterface
public interface UpdateProduct {
    Mono<ProductDTO> apply(String id, ProductDTO productDTO);
}
