package com.msa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msa.domain.Post;

public interface PostRepository extends JpaRepository<Post, Serializable> {

	List<Post> findAllByUserId(Long userId);

	List<Post> findByIdInOrderByIdDesc(List<Long> postIdList);

}
