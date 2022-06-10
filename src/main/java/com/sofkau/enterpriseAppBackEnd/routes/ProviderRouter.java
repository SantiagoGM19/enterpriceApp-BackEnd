package com.sofkau.enterpriseAppBackEnd.routes;

import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import com.sofkau.enterpriseAppBackEnd.usecases.ProviderUseCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProviderRouter {

    @Bean
    public RouterFunction<ServerResponse> saveProviderRouter(SaveProviderUseCase saveProviderUseCase){
        return route(POST("/providers").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProviderDTO.class)
                        .flatMap(providerDTO -> saveProviderUseCase.apply(providerDTO))
                        .flatMap(result ->
                                ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)
                                )
                );
    }

    @Bean
    public RouterFunction<ServerResponse> getAllProvidersRouter(GetAllProvidersUseCase getAllProvidersUseCase){
        return route(GET("/providers"),
                request -> ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllProvidersUseCase.get(), ProviderDTO.class))
                );
    }

    @Bean
    public RouterFunction<ServerResponse> getProviderByIdRouter(GetProviderByIdUseCase getProviderByIdUseCase){
        return route(GET("/providers/{id}"),
                request -> getProviderByIdUseCase.apply(request.pathVariable("id"))
                        .onErrorResume(r -> Mono.empty())
                        .flatMap(providerDTO -> ServerResponse.status(HttpStatus.FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(providerDTO))
                        .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build())
                );
    }

    @Bean
    public RouterFunction<ServerResponse> updateProviderRouter(UpdateProviderUseCase updateProviderUseCase){
        return route(PUT("/providers/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProviderDTO.class)
                        .flatMap(providerDTO -> updateProviderUseCase.apply(request.pathVariable("id"), providerDTO))
                        .flatMap(result -> result.getName()!=null
                                ? ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)
                                :ServerResponse.status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteProviderRouter(DeleteProviderUseCase deleteProviderUseCase){
        return route(DELETE("/providers/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromPublisher(deleteProviderUseCase.apply(request.pathVariable("id"))
                                ,Void.class))
                );
    }
}
