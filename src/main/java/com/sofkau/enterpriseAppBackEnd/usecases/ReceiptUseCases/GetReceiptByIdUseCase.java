package com.sofkau.enterpriseAppBackEnd.usecases.ReceiptUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ReceiptDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ReceiptMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetReceiptByIdUseCase implements Function<String, Mono<ReceiptDTO>> {

    private final IReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Override
    public Mono<ReceiptDTO> apply(String id) {
        return receiptRepository.findById(id).switchIfEmpty(Mono.error(new NoSuchElementException()))
                .onErrorResume(e -> Mono.empty())
                .map(receipt -> receiptMapper.convertEntityToDTO().apply(receipt));
    }
}
