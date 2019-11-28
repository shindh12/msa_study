package com.msa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="post_id")
	private Long postId;
	
	@Column(name="content")
	private String content;
	
	@Column(name="created_at")
	private Date createdAt;
	
	public Comment() {
	}
	
	public Comment(Long userId, Long postId, String content) {
		this.userId = userId;
		this.postId = postId;
		this.content = content;
		this.createdAt = new Date();
	}

	public Comment(Long id, Long userId, Long postId, String content, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.postId = postId;
		this.content = content;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
