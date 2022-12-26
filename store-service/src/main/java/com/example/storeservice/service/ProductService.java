package com.example.storeservice.service;

import com.example.storeservice.dto.in.ProductDtoIn;
import com.example.storeservice.entity.Product;

public interface ProductService {

	Product saveProduct(ProductDtoIn productDtoIn);

}
