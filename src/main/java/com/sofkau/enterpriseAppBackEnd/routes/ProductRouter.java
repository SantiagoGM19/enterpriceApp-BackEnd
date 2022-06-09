package com.sofkau.enterpriseAppBackEnd.routes;

import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases.GetAllProductsUseCase;
import com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases.GetProductByIdUseCase;
import com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases.SaveProductUseCase;
import com.sofkau.enterpriseAppBackEnd.usecases.ProductUseCases.UpdateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> saveProductRouter(SaveProductUseCase saveProductUseCase){
        return route(POST("/products").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(productDTO -> saveProductUseCase.apply(productDTO))
                        .flatMap(result ->
                                ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getAllProductsRouter(GetAllProductsUseCase getAllProductsUseCase){
        return route(GET("/products"),
                request -> ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllProductsUseCase.get(), ProductDTO.class))
                );
    }

    @Bean
    public RouterFunction<ServerResponse> getProductByIdRouter(GetProductByIdUseCase getProductByIdUseCase){
        return route(GET("/products/{id}"),
                request -> getProductByIdUseCase.apply(request.pathVariable("id"))
                        .onErrorResume(r -> Mono.empty())
                        .flatMap(productDTO -> ServerResponse.status(HttpStatus.FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(productDTO))
                        .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build())
                );
    }

    @Bean
    public RouterFunction<ServerResponse> updateProductRouter(UpdateProductUseCase updateProductUseCase){
        return route(PUT("/products/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(productDTO -> updateProductUseCase.apply(request.pathVariable("id"), productDTO))
                        .flatMap(result -> result.getName()!=null
                        ? ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)
                                        :ServerResponse.status(HttpStatus.NOT_FOUND)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
        );
    }
}
