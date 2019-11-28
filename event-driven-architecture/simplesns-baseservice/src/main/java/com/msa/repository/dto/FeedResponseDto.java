package com.msa.repository.dto;

import java.util.List;

public class FeedResponseDto {
	private Integer code;
	private String message;
	private List<FeedData> data;

	public FeedResponseDto() {
		super();
	}

	public FeedResponseDto(Integer code, String message, List<FeedData> data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<FeedData> getData() {
		return data;
	}

	public void setData(List<FeedData> data) {
		this.data = data;
	}

}
