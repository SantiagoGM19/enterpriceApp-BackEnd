package com.sofkau.enterpriseAppBackEnd.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "receipt")
@Data
public class Receipt {
    @Id
    private String receiptId;
    private String name;
    private List<Product> receivedProducts;
    private String providerId;
    private LocalDate date;
}
