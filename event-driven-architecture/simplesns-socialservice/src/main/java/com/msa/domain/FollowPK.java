package com.msa.domain;

import java.io.Serializable;

public class FollowPK implements Serializable {

	private static final long serialVersionUID = -8751183812897660461L;

	private Long followeeId;
	
	private Long followerId;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
