package com.example.shared.common.model;

import com.example.shared.common.ErrorCode;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;

@Data
public class PagingDtoIn {

	private int page = 1;

	@NotNull(message = ErrorCode.STORE_0001)
	@Range(min = 1, message = ErrorCode.STORE_0004)
	private Integer maxSize = 100;

	private String keySearch;

	private String sort;

	private String propertiesSort;
}
