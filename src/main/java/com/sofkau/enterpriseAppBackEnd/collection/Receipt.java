package com.sofkau.enterpriseAppBackEnd.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "receipt")
@Data
public class Receipt {
    @Id
    private String receiptId;
    private String name;
    private List<Product> productsReceived;
    private String providerId;
}
