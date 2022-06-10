package com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.BillInterfaces;

import com.sofkau.enterpriseAppBackEnd.dto.BillDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveBill {
    Mono<BillDTO> apply(BillDTO billDTO);
}
