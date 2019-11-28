package com.msa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.msa.domain.Feed;
import com.msa.domain.Post;
import com.msa.domain.User;
import com.msa.repository.FeedRestRepository;
import com.msa.repository.PostRepository;
import com.msa.repository.UserRepository;
import com.msa.social.service.FeedService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	FeedService feedService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FeedRestRepository feedRestRepository;
	
	@Override
	public Post addPost(Long userId, String title, String content) {
		Post newPost = new Post(userId, title, content);
		
		Post result = postRepository.save(newPost);
		
		// feedService.addFeeds(userId, result.getId());
		feedRestRepository.addFeeds(userId, result.getId());
		
		return result;
	}

	@Override
	public Post getPost(Long id) {
		Optional<Post> result = postRepository.findById(id);
		
		Post post = null;
		if(result.isPresent()) {
			post = result.get();
		}
		
		// bad dependency
		Optional<User> userResult = userRepository.findById(id);
		
		User user = null;
		if(result.isPresent()) {
			user = userResult.get();
		}
		post.setUser(user);
		
		return post;
	}

	@Override
	public List<Post> getPostListByUserId(Long userId) {
		List<Post> postList = postRepository.findAllByUserId(userId);
		
		List<Long> userIdList = postList.stream().map(p -> p.getUserId()).distinct().collect(Collectors.toList());
		
		List<User> userList = userService.getUserListWithFollowInfo(userId, userIdList);
		
		for(Post post: postList) {
			Optional<User> user = userList.stream().filter(u -> u.getId().equals(post.getId())).findFirst();
			if(user.isPresent())
				post.setUser(user.get());
		}
		
		return postList;
	}

	@Override
	@HystrixCommand
	public List<Post> getPostListByFeed(Long userId) {
		// List<Feed> feedList = feedService.getFeedList(userId);
		List<Feed> feedList = feedRestRepository.getFeedList(userId);
		
		List<Long> postIdList = feedList.stream().map(f -> f.getPostId()).collect(Collectors.toList());
		
		List<Post> postList = postRepository.findByIdInOrderByIdDesc(postIdList);
		
		List<Long> userIdList = postList.stream().map(p -> p.getUserId()).distinct().collect(Collectors.toList());
		
		List<User> userList = userService.getUserListWithFollowInfo(userId, userIdList);
		
		for(Post post: postList) {
			Optional<User> user = userList.stream().filter(u -> u.getId().equals(post.getUserId())).findFirst();
			if(user.isPresent())
				post.setUser(user.get());
		}
		
		return postList;
	}

	@Override
	@HystrixCommand
	public List<Post> getPostList(Long userId) {
		List<Post> postList = postRepository.findAll(new Sort(Direction.DESC, "id"));
		
		List<Long> userIdList = postList.stream().map(p -> p.getUserId()).distinct().collect(Collectors.toList());
		
		List<User> userList = null;
		if(userId == null) {
			userList = userService.getUserList(userIdList);
		} else {
			userList = userService.getUserListWithFollowInfo(userId, userIdList);
		}
		
		for(Post post: postList) {
			Optional<User> user = userList.stream().filter(u -> u.getId().equals(post.getUserId())).findFirst();
			if(user.isPresent())
				post.setUser(user.get());
		}
		
		return postList;
	}
	
}
