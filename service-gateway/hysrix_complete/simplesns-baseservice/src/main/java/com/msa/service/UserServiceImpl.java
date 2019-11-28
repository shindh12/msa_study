package com.msa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.domain.AuthToken;
import com.msa.domain.Follow;
import com.msa.domain.User;
import com.msa.repository.FollowRestRepository;
import com.msa.repository.UserRepository;
import com.msa.social.service.FollowService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	FollowService followService;

	@Autowired
	FollowRestRepository followRestRepository;
	
	public User addUser(String username, String password) {
		User user = new User(username, password);
		User result = userRepository.saveAndFlush(user);
		return result;
	}

	@Override
	public User getUser(Long id) {
		Optional<User> result = userRepository.findById(id);
		
		User user = null;
		if(result.isPresent()) {
			user = result.get();
		}
		
		return user;
	}

	@Override
	public List<User> getUserList(List<Long> userIdList) {
		List<User> userList = userRepository.findByIdIn(userIdList);
		
		return userList;
	}
	
	@Override
	public List<User> getUserListWithFollowInfo(Long userId, List<Long> userIdList) {
		List<User> userList = userRepository.findByIdIn(userIdList);
		
		// List<Follow> followList = followService.getFolloweeList(userId, userIdList);
		List<Follow> followList = followRestRepository.getFolloweeList(userId, userIdList);
		
		List<Long> followeeIdList = followList.stream().map(f -> f.getFolloweeId()).collect(Collectors.toList());
		
		for(User user : userList) {
			if(userId.equals(user.getId()))
				continue;
			
			Optional<Long> followeeId = followeeIdList.stream().filter(f -> f.equals(user.getId())).findAny();
			if(followeeId.isPresent()) {
				user.setIsFollow(true);
			} else {
				user.setIsFollow(false);
			}
		}
		
		return userList;
	}

	@Override
	public User getUserByToken(String token) {
		User user = null;
		
		AuthToken authToken = authService.getAuthToken(token);
		
		if(authToken != null) {
			user = userRepository.findById(authToken.getUserId()).get();
		}
		
		return user;
	}
}
