package com.sofkau.enterpriseAppBackEnd.usecases.BillUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.BillDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.BillMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IBillRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.Interfaces.BillInterfaces.SaveBill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveBillUseCase implements SaveBill {

    private final IBillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public Mono<BillDTO> apply(BillDTO billDTO) {
        return billRepository.save(billMapper.convertDTOToEntity().apply(billDTO))
                .map(bill -> billMapper.converEntityToDTO().apply(bill));
    }
}
