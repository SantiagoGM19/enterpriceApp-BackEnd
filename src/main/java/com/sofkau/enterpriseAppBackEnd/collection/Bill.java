package com.sofkau.enterpriseAppBackEnd.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "bill")
@Data
public class Bill {
    @Id
    private String billId;
    private LocalDate date;
    private String clientName;
    private String salePerson;
    private List<Product> productsBought;
    private Double totalPaid;
}
