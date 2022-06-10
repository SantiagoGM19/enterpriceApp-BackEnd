package com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ReceiptInterfaces;

import com.sofkau.enterpriseAppBackEnd.dto.ReceiptDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveReceipt {
    Mono<ReceiptDTO> apply(ReceiptDTO receiptDTO);
}
