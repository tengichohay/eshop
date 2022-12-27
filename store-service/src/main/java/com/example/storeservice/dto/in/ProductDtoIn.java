package com.example.storeservice.dto.in;

import com.example.shared.common.ErrorCode;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static com.example.shared.common.ErrorCode.STORE_0001;
import static com.example.shared.common.ErrorCode.STORE_0004;

@Data
public class ProductDtoIn {

	private String uuidBrand;

	private String uuidCategory;

	@NotNull(message = STORE_0001)
	@NotEmpty(message = STORE_0004)
	private String title;

	@NotNull(message = STORE_0001)
	@NotEmpty(message = STORE_0004)
	private String slug;

	@NotNull(message = STORE_0001)
	@Range(min = 0, max = 1, message = ErrorCode.STORE_0003)
	private Integer moreLove;

	@NotNull(message = STORE_0001)
	private BigDecimal price;

	private BigDecimal oldPrice;

	@NotNull(message = STORE_0001)
	@NotEmpty(message = STORE_0004)
	private String description;

	private Integer totalSell = 0;

	private String color;

	private String vendor;

	private String conditionProduct;

	private Integer status = 1;

	private Integer stock = 0;

	private Integer rating = 5;

	private Integer ratingScore = 0;

}
