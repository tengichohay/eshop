package com.example.storeservice.dto.out;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDtoOut {

	private String uuidProduct;
	private String uuidBrand;
	private String title;
	private String slug;
	private Integer moreLove;
	private BigDecimal price;
	private BigDecimal oldPrice;
	private String description;
	private Integer totalSell;
	private String color;
	private String vendor;
	private String conditionProduct;
	private String createdDate;
	private String updateDate;
	private Integer status;
	private Integer stock;
	private Integer rating;
	private Integer ratingScore;
	private String uuidCategory;

}
