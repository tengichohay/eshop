package com.example.storeservice.controller;


import com.example.shared.common.context.DataContextHelper;
import com.example.shared.common.response.ResponseEntites;
import com.example.shared.controller.AbstractResponseController;
import com.example.storeservice.dto.in.ProductDtoIn;
import com.example.storeservice.entity.Product;
import com.example.storeservice.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/product")
public class ProductController extends AbstractResponseController {

	private final ProductService productService;

	protected ProductController(ResponseEntites<Object> responseEntites, DataContextHelper dataContextHelper,
								ProductService productService) {
		super(responseEntites, dataContextHelper);
		this.productService = productService;
	}

	@PostMapping("/add")
	@ApiOperation(value = "Thêm mới 1 sản phẩm")
	public DeferredResult<ResponseEntity<?>> addProduct(HttpServletRequest request,
														@RequestHeader("Authorization") String authorization,
														@RequestBody @Valid ProductDtoIn productDtoIn) {
		return responseEntityDeferredResult(() -> {
			log.info("[REQUEST] --> url: {}, requestBody: {}", request.getRequestURI(), productDtoIn);
			Product product = productService.saveProduct(productDtoIn);
			log.info("[RESPONSE] --> SUCCESS!");
			return product;
		});
	}

}
