package com.example.shared.common.model;

import com.example.shared.common.Common;
import com.example.shared.common.ConstantString;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Getter
public class PagingDTO<T> {

	private int page;

	private int maxSize;

	private long totalElement;

	private long totalPages;

	private String sort;

	private String propertiesSort;

	private List<T> data;

	public static Pageable build(PagingDtoIn pagingDtoIn) {
		Sort sort = Sort.unsorted();
		if (!Common.isNullOrEmpty(pagingDtoIn.getPropertiesSort())) {
			Sort.Direction directionSort = Sort.Direction.fromString(ObjectUtils.defaultIfNull(pagingDtoIn.getSort(), Sort.Direction.ASC.name()));
			List<String> properties = Arrays.stream(pagingDtoIn.getPropertiesSort().split(",")).collect(Collectors.toList());
			List<Sort.Order> order = new ArrayList<>();
			properties.forEach(property -> order.add(new Sort.Order(directionSort, property)));
			sort = Sort.by(order);
		}
		return PageRequest.of(pagingDtoIn.getPage(), pagingDtoIn.getMaxSize(), sort);
	}

	public static <T> PagingDTO<T> get(Page<?> pageResult, PagingDtoIn pagingDtoIn, List<T> result) {
		return PagingDTO.<T>builder()
				.page(pagingDtoIn.getPage())
				.maxSize(pagingDtoIn.getMaxSize())
				.totalElement(pageResult.getTotalElements())
				.totalPages(pageResult.getTotalPages())
				.propertiesSort(pagingDtoIn.getPropertiesSort())
				.sort(pagingDtoIn.getSort())
				.data(result)
				.build();
	}

	@Override
	public String toString() {
		return new GsonBuilder().setDateFormat(ConstantString.DDMMYYYY_HHMMSS).create().toJson(this);
	}
}
