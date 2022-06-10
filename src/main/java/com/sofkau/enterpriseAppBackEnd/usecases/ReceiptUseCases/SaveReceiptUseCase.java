package com.sofkau.enterpriseAppBackEnd.usecases.ReceiptUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.ReceiptDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ReceiptMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IReceiptRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.ReceiptInterfaces.SaveReceipt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveReceiptUseCase implements SaveReceipt {

    private final IReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Override
    public Mono<ReceiptDTO> apply(ReceiptDTO receiptDTO) {
        return receiptRepository.save(receiptMapper.convertDTOToEntity().apply(receiptDTO))
                .map(receipt -> receiptMapper.convertEntityToDTO().apply(receipt));
    }
}
