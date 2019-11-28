package com.msa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "feed")
@IdClass(FeedPK.class)
public class Feed implements Serializable {

	private static final long serialVersionUID = -4622872125602745458L;

	@Id
	@Column(name="user_id")
	private Long userId;
	
	@Id
	@Column(name="followee_id")
	private Long followeeId;
	
	@Id
	@Column(name="post_id")
	private Long postId;
	
	@Column(name="created_at")
	private Date createdAt;

	public Feed() {
		super();
	}

	public Feed(Long followerId, Long followeeId, Long postId) {
		this.userId = followerId;
		this.followeeId = followeeId;
		this.postId = postId;
		this.createdAt = new Date();
	}

	public Feed(Long userId, Long followeeId, Long postId, Date createdAt) {
		super();
		this.userId = userId;
		this.followeeId = followeeId;
		this.postId = postId;
		this.createdAt = createdAt;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
