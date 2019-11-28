package com.msa.service;

import java.util.List;

import com.msa.domain.Post;

public interface PostService {
	
	public Post addPost(Long userId, String title, String content);
	
	public Post getPost(Long id);
	
	public List<Post> getPostListByUserId(Long userId);
	
	public List<Post> getPostListByFeed(Long userId);

	public List<Post> getPostList(Long userId);
}
