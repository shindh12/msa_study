package com.msa.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.msa.controller.dto.PostDto;
import com.msa.controller.dto.ResultDto;
import com.msa.domain.AuthToken;
import com.msa.domain.Post;
import com.msa.repository.PostRepository;
import com.msa.service.AuthService;
import com.msa.service.PostService;

@RefreshScope
@RestController
public class PostController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PostService postService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	PostRepository postRepository;
	
	@Value("${env}")
	String env;
	
	@PostMapping("/post")
	public ResultDto addPost(@RequestBody PostDto dto, @RequestHeader(value="accesstoken") String accesstoken) {
		AuthToken authToken = authService.getAuthToken(accesstoken);
		if(authToken == null) {
			return new ResultDto(4002, "OK", "Authentication Failed");
		}
		
		Post result = postService.addPost(authToken.getUserId(), dto.getTitle(), dto.getContent());
		
		return new ResultDto(200, "Success", result);
	}
	
	@GetMapping("/post")
	public ResultDto getPostList(@RequestHeader(value="accesstoken", required=false) String accesstoken) {
		logger.info("this is from git " + env);
		
		AuthToken authToken = null;
		List<Post> postList;
		
		if(accesstoken != null && !accesstoken.equals("undefined")) {
			authToken = authService.getAuthToken(accesstoken);
			if(authToken == null) {
				return new ResultDto(4002, "OK", "Authentication Failed");
			}
			postList = postService.getPostList(authToken.getUserId());
		} else {
			postList = postService.getPostList(null);
		}

		return new ResultDto(200, "Success", postList);
	}
	
	@GetMapping("/post/{postId}/simple")
	public ResultDto getSimplePost(@PathVariable("postId") String postId) {
		Optional<Post> postResult = postRepository.findById(Long.valueOf(postId));

		return new ResultDto(200, "Success", postResult.get());
	}
	
	@GetMapping("/post/my")
	public ResultDto getMyPostList(@RequestHeader(value="accesstoken") String accesstoken) {
		AuthToken authToken = authService.getAuthToken(accesstoken);
		if(authToken == null) {
			return new ResultDto(4002, "OK", "Authentication Failed");
		}
		
		List<Post> postList = postService.getPostListByUserId(authToken.getUserId());
		
		return new ResultDto(200, "Success", postList);
	}
	
	@GetMapping("/post/feed") 
	public ResultDto getFeedPostList(@RequestHeader(value="accesstoken") String accesstoken) {
		AuthToken authToken = authService.getAuthToken(accesstoken);
		if(authToken == null) {
			return new ResultDto(4002, "OK", "Authentication Failed");
		}
		
		List<Post> postList = postService.getPostListByFeed(authToken.getUserId());
		
		return new ResultDto(200, "Success", postList);
	}
	
	@GetMapping("/post/{postId}")
	public ResultDto getPost(@PathVariable("postId") String postId) {
		
		Post post = postService.getPost(Long.valueOf(postId));
		
		return new ResultDto(200, "Success", post);
	}
}
