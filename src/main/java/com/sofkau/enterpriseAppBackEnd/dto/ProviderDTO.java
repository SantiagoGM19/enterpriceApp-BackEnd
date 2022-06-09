package com.sofkau.enterpriseAppBackEnd.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProviderDTO {
    private String providerId = UUID.randomUUID().toString().substring(0, 10);
    private String name;
    private String phone;
}
