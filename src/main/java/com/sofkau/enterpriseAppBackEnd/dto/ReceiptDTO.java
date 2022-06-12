package com.sofkau.enterpriseAppBackEnd.dto;

import com.sofkau.enterpriseAppBackEnd.collection.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class ReceiptDTO {
    private String receiptId = UUID.randomUUID().toString().substring(0, 10);
    private String name;
    private List<Product> receivedProducts;
    private String providerId;
    private LocalDate date;
}
