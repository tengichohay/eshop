package com.example.storeservice.dto.in;

import com.example.shared.common.ErrorCode;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

import static com.example.shared.common.ErrorCode.STORE_0004;

@Data
public class ProductDtoIn {

	@NotNull(message = ErrorCode.STORE_0001)
	@NotEmpty(message = STORE_0004)
	private String title;

	@NotNull(message = ErrorCode.STORE_0001)
	@NotEmpty(message = STORE_0004)
	private String slug;

	@NotNull(message = ErrorCode.STORE_0001)
	@Range(min = 0, max = 1, message = ErrorCode.STORE_0003)
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

}
