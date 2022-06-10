package com.sofkau.enterpriseAppBackEnd.routes;


import com.sofkau.enterpriseAppBackEnd.dto.BillDTO;
import com.sofkau.enterpriseAppBackEnd.usecases.BillUseCases.GetAllBillsUseCase;
import com.sofkau.enterpriseAppBackEnd.usecases.BillUseCases.GetBillByIdUseCase;
import com.sofkau.enterpriseAppBackEnd.usecases.BillUseCases.SaveBillUseCase;
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
public class BillRouter {

    @Bean
    public RouterFunction<ServerResponse> saveBillRouter(SaveBillUseCase saveBillUseCase){
        return route(POST("/bills").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BillDTO.class)
                        .flatMap(billDTO -> saveBillUseCase.apply(billDTO))
                        .flatMap(result ->
                                ServerResponse.status(HttpStatus.CREATED)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)
                                )
                );
    }

    @Bean
    public RouterFunction<ServerResponse> getAllBillsRouter(GetAllBillsUseCase getAllBillsUseCase){
        return route(GET("/bills"),
                request -> ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllBillsUseCase.get(), BillDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getBillByIdRouter(GetBillByIdUseCase getBillByIdUseCase){
        return route(GET("/bills/{id}"),
                request -> getBillByIdUseCase.apply(request.pathVariable("id"))
                        .onErrorResume(r -> Mono.empty())
                        .flatMap(billDTO -> ServerResponse.status(HttpStatus.FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(billDTO))
                        .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build())
                );
    }
}
