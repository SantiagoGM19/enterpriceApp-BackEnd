package com.sofkau.enterpriseAppBackEnd.usecases.BillUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.BillDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.BillMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IBillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllBillsUseCase implements Supplier<Flux<BillDTO>> {

    private final IBillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public Flux<BillDTO> get() {
        return billRepository.findAll().map(bill -> billMapper.converEntityToDTO().apply(bill));
    }
}
