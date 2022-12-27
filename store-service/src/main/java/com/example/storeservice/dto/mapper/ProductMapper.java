package com.example.storeservice.dto.mapper;

import com.example.shared.common.ConstantString;
import com.example.storeservice.dto.in.ProductDtoIn;
import com.example.storeservice.dto.out.ProductDtoOut;
import com.example.storeservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

	Product productDtoInToProduct(ProductDtoIn productDtoIn);

	@Mapping(target = "createdDate", dateFormat = ConstantString.DDMMYYYY_HHMMSS)
	@Mapping(target = "updateDate", dateFormat = ConstantString.DDMMYYYY_HHMMSS)
	ProductDtoOut productToProductDtoOut(Product product);

}
