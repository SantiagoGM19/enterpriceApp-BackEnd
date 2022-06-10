package com.sofkau.enterpriseAppBackEnd.mapper;

import com.sofkau.enterpriseAppBackEnd.collection.Bill;
import com.sofkau.enterpriseAppBackEnd.dto.BillDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BillMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Function<Bill, BillDTO> converEntityToDTO(){
        return bill -> modelMapper.map(bill, BillDTO.class);
    }

    public Function<BillDTO, Bill> convertDTOToEntity(){
        return billDTO -> modelMapper.map(billDTO, Bill.class);
    }
}
