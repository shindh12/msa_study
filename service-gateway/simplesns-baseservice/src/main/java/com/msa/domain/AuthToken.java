package com.msa.domain;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.msa.util.EncryptionUtil;

@Entity
@Table(name = "token")
public class AuthToken {
	
	@Id
	@Column(name="token")
	private String token;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="created_at")
	private Date createdAt;
	
	public AuthToken() {
		super();
	}

	public AuthToken(Long userId, String seed) {
		try {
			this.token = EncryptionUtil.sha256(seed);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.userId = userId;
		this.createdAt = new Date();
	}
	
	public AuthToken(String token, Long userId) {
		super();
		this.token = token;
		this.userId = userId;
	}

	public AuthToken(String token, Long userId, Date createdAt) {
		super();
		this.token = token;
		this.userId = userId;
		this.createdAt = createdAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
