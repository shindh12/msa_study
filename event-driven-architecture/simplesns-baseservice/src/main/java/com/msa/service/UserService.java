package com.msa.service;

import java.util.List;

import com.msa.domain.User;

public interface UserService {	
	
	public User addUser(String username, String password);
	
	public User getUser(Long id);

	public List<User> getUserList(List<Long> userIdList);

	public User getUserByToken(String token);

	public List<User> getUserListWithFollowInfo(Long userId, List<Long> userIdList);

}
