package com.sofkau.enterpriseAppBackEnd.usecases.ReceiptUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ReceiptDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ReceiptMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllReceiptsUseCase implements Supplier<Flux<ReceiptDTO>> {

    private final IReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Override
    public Flux<ReceiptDTO> get() {
        return receiptRepository.findAll().map(receipt -> receiptMapper.convertEntityToDTO().apply(receipt));
    }
}
