package com.example.storeservice.controller;


import com.example.shared.common.context.DataContextHelper;
import com.example.shared.common.model.PagingDTO;
import com.example.shared.common.model.PagingDtoIn;
import com.example.shared.common.response.ResponseEntites;
import com.example.shared.controller.AbstractResponseController;
import com.example.storeservice.dto.in.ProductDtoIn;
import com.example.storeservice.dto.out.ProductDtoOut;
import com.example.storeservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

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
	@PreAuthorize("hasAnyAuthority('ADMIN_MANAGE')")
	public DeferredResult<ResponseEntity<?>> addProduct(HttpServletRequest request,
														@RequestBody @Valid ProductDtoIn productDtoIn) {
		return responseEntityDeferredResult(() -> {
			log.info("[REQUEST] --> url: {}, requestBody: {}", request.getRequestURI(), productDtoIn);
			productService.saveProduct(productDtoIn);
			log.info("[RESPONSE] --> SUCCESS!");
			return new HashMap<>();
		});
	}

	@GetMapping("/list")
	public DeferredResult<ResponseEntity<?>> listProduct(HttpServletRequest request,
														 PagingDtoIn pagingDtoIn) {
		return responseEntityDeferredResult(() -> {
			log.info("[REQUEST] --> url: {}", request.getRequestURI());
			PagingDTO<ProductDtoOut> result = productService.listAllProducts(pagingDtoIn);
			log.info("[RESPONSE] --> SUCCESS!");
			return result;
		});
	}

}
