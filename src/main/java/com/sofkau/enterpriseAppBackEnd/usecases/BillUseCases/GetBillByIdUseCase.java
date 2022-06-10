package com.sofkau.enterpriseAppBackEnd.usecases.BillUseCases;

import com.sofkau.enterpriseAppBackEnd.dto.BillDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.BillMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IBillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetBillByIdUseCase implements Function<String, Mono<BillDTO>> {

    private final IBillRepository billRepository;
    private final BillMapper billMapper;

    @Override
    public Mono<BillDTO> apply(String id) {
        return billRepository.findById(id).switchIfEmpty(Mono.error(new NoSuchElementException()))
                .onErrorResume(e -> Mono.empty())
                .map(bill -> billMapper.converEntityToDTO().apply(bill));
    }
}
