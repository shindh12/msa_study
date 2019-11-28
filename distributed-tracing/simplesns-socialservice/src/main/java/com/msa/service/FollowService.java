package com.msa.service;

import java.util.List;

import com.msa.domain.Follow;

public interface FollowService {
	
	public Follow addFollow(Long followeeId, Long followerId);
	
	public List<Follow> getFollowerList(Long followeeId);
	
	public void deleteFollow(Long followeeId, Long followerId);

	public List<Follow> getFolloweeList(Long userId, List<Long> userIdList);
	
}
