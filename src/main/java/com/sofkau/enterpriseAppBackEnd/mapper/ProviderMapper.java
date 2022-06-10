package com.sofkau.enterpriseAppBackEnd.mapper;

import com.sofkau.enterpriseAppBackEnd.collection.Provider;
import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProviderMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Function<Provider, ProviderDTO> convertEntityToDTO(){
        return provider -> modelMapper.map(provider, ProviderDTO.class);
    }

    public Function<ProviderDTO, Provider> convertDTOToEntity(){
        return providerDTO -> modelMapper.map(providerDTO, Provider.class);
    }
}
