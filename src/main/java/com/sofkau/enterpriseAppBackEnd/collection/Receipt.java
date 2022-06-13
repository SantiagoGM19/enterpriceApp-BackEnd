package com.sofkau.enterpriseAppBackEnd.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "receipt")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Receipt {
    @Id
    private String receiptId;
    private String name;
    private List<Product> receivedProducts;
    private String providerId;
    private LocalDate date;
}
