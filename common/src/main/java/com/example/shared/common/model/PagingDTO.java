package com.example.shared.common.model;

import com.example.shared.common.ConstantString;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagingDTO<T> {

	private int page;

	private int maxSize;

	private long totalElement;

	private long totalPages;

	private String sort;

	private String propertiesSort;

	private List<T> data;

	public static <T> PagingDTO<T> get(Long total, PagingDtoIn pagingDtoIn, List<T> rs) {
		if (total == null) {
			total = 0L;
		}
		long totalPages = total / pagingDtoIn.getMaxSize();
		if (total % pagingDtoIn.getMaxSize() != 0) {
			totalPages++;
		}
		PagingDTO<T> pagingDTO = new PagingDTO<>();
		pagingDTO.setPage(pagingDtoIn.getPage());
		pagingDTO.setMaxSize(pagingDtoIn.getMaxSize());
		pagingDTO.setTotalPages(totalPages);
		pagingDTO.setTotalElement(total);
		if (pagingDtoIn.getPage() > totalPages) {
			pagingDTO.setData(new ArrayList<>());
			return pagingDTO;
		}
		pagingDTO.setData(rs);
		return pagingDTO;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setDateFormat(ConstantString.DDMMYYYY_HHMMSS).create().toJson(this);
	}
}
