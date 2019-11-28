package com.msa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msa.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Serializable> {

	List<Comment> findByPostId(Long postId);

}
