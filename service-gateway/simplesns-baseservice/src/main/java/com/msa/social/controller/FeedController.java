package com.msa.social.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.controller.dto.ResultDto;
import com.msa.domain.Feed;
import com.msa.social.controller.dto.FeedRequestDto;
import com.msa.social.service.FeedService;

@RestController
public class FeedController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	FeedService feedService;
	
	@PostMapping("/feed")
	public ResultDto addFeeds(@RequestBody FeedRequestDto dto) {
		feedService.addFeeds(dto.getUserId(), dto.getPostId());
		
		return new ResultDto(200, "OK", "Success");
	}
	
	@GetMapping("/feed")
	public ResultDto getFeeds(@RequestParam Long userId) {
		logger.info("getFeeds called!!!");
		List<Feed> feedList = feedService.getFeedList(userId);
		
		return new ResultDto(200, "OK", feedList);
	}
}
