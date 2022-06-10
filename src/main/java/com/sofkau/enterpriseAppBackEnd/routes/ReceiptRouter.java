package com.sofkau.enterpriseAppBackEnd.routes;

import com.sofkau.enterpriseAppBackEnd.dto.ReceiptDTO;
import com.sofkau.enterpriseAppBackEnd.usecases.ReceiptUseCases.GetAllReceiptsUseCase;
import com.sofkau.enterpriseAppBackEnd.usecases.ReceiptUseCases.GetReceiptByIdUseCase;
import com.sofkau.enterpriseAppBackEnd.usecases.ReceiptUseCases.SaveReceiptUseCase;
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
public class ReceiptRouter {

    @Bean
    public RouterFunction<ServerResponse> saveReceiptRouter(SaveReceiptUseCase saveReceiptUseCase){
        return route(POST("/receipts").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ReceiptDTO.class)
                        .flatMap(receiptDTO -> saveReceiptUseCase.apply(receiptDTO))
                        .flatMap(result ->
                                ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)
                                )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getAllReceiptsRouter(GetAllReceiptsUseCase getAllReceiptsUseCase){
        return route(GET("/receipts").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllReceiptsUseCase.get(), ReceiptDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getReceiptByIdRouter(GetReceiptByIdUseCase getReceiptByIdUseCase){
        return route(GET("/receipts/{id}"),
                request -> getReceiptByIdUseCase.apply(request.pathVariable("id"))
                        .onErrorResume(r -> Mono.empty())
                        .flatMap(receiptDTO -> ServerResponse.status(HttpStatus.FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(receiptDTO))
                        .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build())
                );
    }
}
