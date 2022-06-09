package com.sofkau.enterpriseAppBackEnd.routes;

import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases.SaveProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> saveProductRouter(SaveProductUseCase saveProductUseCase){
        return route(POST("/save/product").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(productDTO -> saveProductUseCase.apply(productDTO))
                        .flatMap(result ->
                                ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
        );
    }
}
