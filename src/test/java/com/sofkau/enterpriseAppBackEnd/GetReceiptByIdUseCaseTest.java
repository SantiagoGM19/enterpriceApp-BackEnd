package com.sofkau.enterpriseAppBackEnd;

import com.sofkau.enterpriseAppBackEnd.collection.Product;
import com.sofkau.enterpriseAppBackEnd.collection.Receipt;
import com.sofkau.enterpriseAppBackEnd.dto.ReceiptDTO;
import com.sofkau.enterpriseAppBackEnd.mapper.ReceiptMapper;
import com.sofkau.enterpriseAppBackEnd.repository.IReceiptRepository;
import com.sofkau.enterpriseAppBackEnd.usecases.ReceiptUseCases.GetReceiptByIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GetReceiptByIdUseCaseTest {
    private GetReceiptByIdUseCase getReceiptByIdUseCase;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Mock
    IReceiptRepository receiptRepository;

    @BeforeEach
    void setUp(){
        getReceiptByIdUseCase = new GetReceiptByIdUseCase(receiptRepository, receiptMapper);
    }

    @Test
    public void getReceiptByIdTest(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("prdrs-2","videoCard","rtx 3000", 5, 40, 20, 2000.0, null));
        Receipt receipt = new Receipt("r-15","receipt1",products,"pr-12", LocalDate.now());

        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setReceiptId(receipt.getReceiptId());
        receiptDTO.setName(receipt.getName());
        receiptDTO.setReceivedProducts(receipt.getReceivedProducts());
        receiptDTO.setProviderId(receipt.getProviderId());
        receiptDTO.setDate(receipt.getDate());

        Mockito.when(receiptRepository.findById(receipt.getReceiptId())).thenReturn(Mono.just(receipt));

        Mono<ReceiptDTO> publisher = getReceiptByIdUseCase.apply(receipt.getReceiptId());

        StepVerifier
                .create(publisher)
                .expectNext(receiptDTO)
                .verifyComplete();

        Mockito.verify(receiptRepository).findById(receipt.getReceiptId());
    }
}
