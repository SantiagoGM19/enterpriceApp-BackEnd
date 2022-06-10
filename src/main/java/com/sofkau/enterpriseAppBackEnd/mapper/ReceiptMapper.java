package com.sofkau.enterpriseAppBackEnd.mapper;

import com.sofkau.enterpriseAppBackEnd.collection.Provider;
import com.sofkau.enterpriseAppBackEnd.collection.Receipt;
import com.sofkau.enterpriseAppBackEnd.dto.ProviderDTO;
import com.sofkau.enterpriseAppBackEnd.dto.ReceiptDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReceiptMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Function<Receipt, ReceiptDTO> convertEntityToDTO(){
        return receipt -> modelMapper.map(receipt, ReceiptDTO.class);
    }

    public Function<ReceiptDTO, Receipt> convertDTOToEntity(){
        return receiptDTO -> modelMapper.map(receiptDTO, Receipt.class);
    }
}
