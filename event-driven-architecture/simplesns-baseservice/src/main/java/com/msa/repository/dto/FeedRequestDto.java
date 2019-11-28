package com.msa.repository.dto;

public class FeedRequestDto {
	private Long userId;
	private Long postId;

	public FeedRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeedRequestDto(Long userId, Long postId) {
		super();
		this.userId = userId;
		this.postId = postId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

}
