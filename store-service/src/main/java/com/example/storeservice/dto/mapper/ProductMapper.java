package com.example.storeservice.dto.mapper;

import com.example.storeservice.dto.in.ProductDtoIn;
import com.example.storeservice.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

	Product productDtoInToProduct(ProductDtoIn productDtoIn);

}
