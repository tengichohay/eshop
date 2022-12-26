package com.example.storeservice.service.iml;

import com.example.storeservice.dto.in.ProductDtoIn;
import com.example.storeservice.dto.mapper.ProductMapper;
import com.example.storeservice.entity.Product;
import com.example.storeservice.repository.ProductRepository;
import com.example.storeservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductionServiceImpl implements ProductService {

	private final ProductMapper productMapper;
	private final ProductRepository productRepository;

	@Autowired
	public ProductionServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
		this.productMapper = productMapper;
		this.productRepository = productRepository;
	}

	@Override
	public Product saveProduct(ProductDtoIn productDtoIn) {
		Product product = productMapper.productDtoInToProduct(productDtoIn);
//		productRepository.save();
		return product;
	}
}
