package com.example.storeservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
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
	private String condition;
	private Date createdDate;
	private Date updateDate;
	private String status;
	private Integer stock;
	private Integer rating;
	private Integer ratingScore;
	private String uuidCategory;

}
