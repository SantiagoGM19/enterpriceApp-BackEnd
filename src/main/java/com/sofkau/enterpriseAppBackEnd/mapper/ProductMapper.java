package com.sofkau.enterpriseAppBackEnd.mapper;

import com.sofkau.enterpriseAppBackEnd.collection.Product;
import com.sofkau.enterpriseAppBackEnd.dto.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Function<Product, ProductDTO> convertEntityToDTO(){
        return product -> modelMapper.map(product, ProductDTO.class);
    }

    public Function<ProductDTO, Product> convertDTOToEntity(){
        return productDTO -> modelMapper.map(productDTO, Product.class);
    }
}
