package com.sofkau.enterpriseAppBackEnd.collection;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "providers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Provider {
    @Id
    private String providerId = UUID.randomUUID().toString().substring(0, 10);
    private String name;
    private String phone;
}
