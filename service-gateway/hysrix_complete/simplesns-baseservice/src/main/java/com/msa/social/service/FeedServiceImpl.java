package com.msa.social.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.domain.Feed;
import com.msa.domain.Follow;
import com.msa.social.repository.FeedRepository;

@Service
public class FeedServiceImpl implements FeedService {
	
	@Autowired
	FollowService followService;
	
	@Autowired
	FeedRepository feedRepository;

	@Override
	public void addFeeds(Long followeeId, Long postId) {
		Feed feed = new Feed(followeeId, followeeId, postId);
		feedRepository.save(feed);
		
		List<Follow> followList = followService.getFollowerList(followeeId);
		
		List<Feed> feedList = new ArrayList<Feed>();
		for(Follow follow : followList) {
			feedList.add(new Feed(follow.getFollowerId(), followeeId, postId));
		}
		
		feedRepository.saveAll(feedList);
	}

	@Override
	public List<Feed> getFeedList(Long userId) {
		List<Feed> feedList = feedRepository.findByUserId(userId);
		return feedList;
	}

	@Override
	public void deleteFeeds(Long userId, Long followeeId) {
		feedRepository.deleteByUserIdAndFolloweeId(userId, followeeId);
	}

}
