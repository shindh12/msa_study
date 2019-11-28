package com.msa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.domain.Comment;
import com.msa.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public Comment addComment(Long userId, Long postId, String content) {
		
		Comment comment = new Comment(userId, postId, content);

		Comment result = commentRepository.saveAndFlush(comment);
		
		return result;
	}

	@Override
	public Comment getComment(Long id) {
		Optional<Comment> result = commentRepository.findById(id);
		
		Comment comment = null;
		if(result.isPresent()) {
			comment = result.get();
		}
		
		return comment;
	}

	@Override
	public List<Comment> getCommentListByPostId(Long postId) {
		List<Comment> commentList = commentRepository.findByPostId(postId);
		return commentList;
	}

	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

}
