package com.msa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msa.domain.AuthToken;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Serializable> {

	void deleteByUserId(Long userId);

}
