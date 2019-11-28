package com.msa.repository.dto;

public class FeedData {
	private Long userId;
	private Long followeeId;
	private Long postId;

	public FeedData() {
		super();
	}

	public FeedData(Long userId, Long followeeId, Long postId) {
		super();
		this.userId = userId;
		this.followeeId = followeeId;
		this.postId = postId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFolloweeId() {
		return followeeId;
	}

	public void setFolloweeId(Long followeeId) {
		this.followeeId = followeeId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

}
