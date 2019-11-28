package com.msa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "follow")
@IdClass(FollowPK.class)
public class Follow implements Serializable {

	private static final long serialVersionUID = -1496038274327634951L;

	@Id
	@Column(name="followee_id")
	private Long followeeId;
	@Id
	@Column(name="follower_id")
	private Long followerId;
	
	@Column(name="created_at")
	private Date createdAt;
	
	public Follow() {
		super();
	}

	public Follow(Long followeeId, Long followerId) {
		this.followeeId = followeeId;
		this.followerId = followerId;
		this.createdAt = new Date();
	}

	public Follow(Long followeeId, Long followerId, Date createdAt) {
		super();
		this.followeeId = followeeId;
		this.followerId = followerId;
		this.createdAt = createdAt;
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
