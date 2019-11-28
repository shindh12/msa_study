package com.msa.service;

import java.util.List;

import com.msa.domain.Feed;

public interface FeedService {

	public void addFeeds(Long followeeId, Long postId);
	
	public List<Feed> getFeedList(Long userId);
	
	public void deleteFeeds(Long userId, Long followeeId);
}
