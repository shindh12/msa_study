package com.msa.repository.dto;

public class FollowData {
	private Long followeeId;
	private Long followerId;

	public FollowData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FollowData(Long followeeId, Long followerId) {
		super();
		this.followeeId = followeeId;
		this.followerId = followerId;
	}

	public Long getFolloweeId() {
		return followeeId;
	}

	public void setFolloweeId(Long followeeId) {
		this.followeeId = followeeId;
	}

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

}
