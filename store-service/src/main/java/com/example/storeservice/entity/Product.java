package com.example.storeservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UUID_PRODUCT")
	private String uuidProduct;

	@Column(name = "UUID_BRAND")
	private String uuidBrand;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "SLUG")
	private String slug;

	@Column(name = "MORE_LOVE")
	private Integer moreLove;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "OLD_PRICE")
	private BigDecimal oldPrice;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "TOTAL_SELL")
	private Integer totalSell;

	@Column(name = "COLOR")
	private String color;

	@Column(name = "VENDOR")
	private String vendor;

	@Column(name = "CONDITION_PRODUCT")
	private String conditionProduct;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "UPDATE_DATE")
	private Timestamp updateDate;

	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "STOCK")
	private Integer stock;

	@Column(name = "RATING")
	private Integer rating;

	@Column(name = "RATING_SCORE")
	private Integer ratingScore;

	@Column(name = "UUID_CATEGORY")
	private String uuidCategory;

}
