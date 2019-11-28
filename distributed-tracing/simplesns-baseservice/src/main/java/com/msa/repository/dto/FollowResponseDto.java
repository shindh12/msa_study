package com.msa.repository.dto;

import java.util.List;

public class FollowResponseDto {
	private Integer code;
	private String message;
	private List<FollowData> data;

	public FollowResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FollowResponseDto(Integer code, String message, List<FollowData> data) {
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

	public List<FollowData> getData() {
		return data;
	}

	public void setData(List<FollowData> data) {
		this.data = data;
	}

}
