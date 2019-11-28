package com.msa.service;

import java.util.List;

import com.msa.domain.Comment;

public interface CommentService {

	public Comment addComment(Long userId, Long postId, String content);
	
	public Comment getComment(Long id);
	
	public List<Comment> getCommentListByPostId(Long postId);
	
	public void deleteComment(Long id);
}
