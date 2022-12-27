package com.example.storeservice.service.iml;

import com.example.shared.common.Common;
import com.example.shared.common.model.PagingDTO;
import com.example.shared.common.model.PagingDtoIn;
import com.example.storeservice.dto.in.ProductDtoIn;
import com.example.storeservice.dto.mapper.ProductMapper;
import com.example.storeservice.dto.out.ProductDtoOut;
import com.example.storeservice.entity.Product;
import com.example.storeservice.repository.ProductRepository;
import com.example.storeservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
	public void saveProduct(ProductDtoIn productDtoIn) {
		Product product = productMapper.productDtoInToProduct(productDtoIn);
		product.setUuidProduct(Common.GenerateUUID());
		product.setCreatedDate(Common.getCurrentTime());
		productRepository.save(product);
	}

	@Override
	public PagingDTO<ProductDtoOut> listAllProducts(PagingDtoIn pagingDtoIn) {
		Page<Product> listAllProducts = productRepository.findAll(PagingDTO.build(pagingDtoIn));
		List<ProductDtoOut> result = listAllProducts.getContent()
				.stream()
				.map(productMapper::productToProductDtoOut)
				.collect(Collectors.toList());
		return PagingDTO.get(listAllProducts, pagingDtoIn, result);
	}
}
