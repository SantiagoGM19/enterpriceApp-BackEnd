package com.sofkau.enterpriseAppBackEnd.dto;

import com.sofkau.enterpriseAppBackEnd.collection.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class BillDTO {
    private String billId = UUID.randomUUID().toString().substring(0, 10);
    private LocalDate date;
    private String clientName;
    private String salePerson;
    private List<Product> boughtProducts;
    private Double totalPaid;
}
