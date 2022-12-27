package com.example.storeservice.service;

import com.example.shared.common.model.PagingDTO;
import com.example.shared.common.model.PagingDtoIn;
import com.example.storeservice.dto.in.ProductDtoIn;
import com.example.storeservice.dto.out.ProductDtoOut;

public interface ProductService {

	void saveProduct(ProductDtoIn productDtoIn);

	PagingDTO<ProductDtoOut> listAllProducts(PagingDtoIn pagingDtoIn);

}
