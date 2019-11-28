package com.msa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.controller.dto.FollowDto;
import com.msa.controller.dto.ResultDto;
import com.msa.domain.AuthToken;
import com.msa.domain.Follow;
import com.msa.repository.AuthTokenRestRepository;
import com.msa.repository.BaseServiceFeignClient;
import com.msa.repository.dto.AuthTokenData;
import com.msa.repository.dto.ResponseDto;
import com.msa.service.FollowService;

@CrossOrigin
@RestController
public class FollowController {
	
	@Autowired
	FollowService followService;
	
	@Autowired
	AuthTokenRestRepository authTokenRestRepository;
	
	@Autowired
	BaseServiceFeignClient baseServiceFeignClient;
	
	@PostMapping("/follow")
	public ResultDto addFollow(@RequestBody FollowDto dto, @RequestHeader(value="accesstoken") String accesstoken) {
		ResponseDto<AuthTokenData> result = baseServiceFeignClient.getAuthToken(accesstoken);
		
		if(result.getData() == null) {
			return new ResultDto(4002, "OK", "Authentication Failed");
		}
		
		followService.addFollow(dto.getFolloweeId(), result.getData().getUserId());
		
		return new ResultDto(200, "OK", "Success");
	}
	
	@DeleteMapping("/follow")
	public ResultDto deleteFollow(@RequestBody FollowDto dto, @RequestHeader(value="accesstoken") String accesstoken) {
		// AuthToken authToken = authService.getAuthToken(accesstoken);
		AuthToken authToken = authTokenRestRepository.getAuthToken(accesstoken);
				
		if(authToken == null) {
			return new ResultDto(4002, "OK", "Authentication Failed");
		}
		
		followService.deleteFollow(dto.getFolloweeId(), authToken.getUserId());
		
		return new ResultDto(200, "OK", "Success");
	}
	
	@GetMapping("/followee")
	public ResultDto getFolloweeList(@RequestParam Long userId, @RequestParam String userIds) {
		String[] idArray = userIds.split(",");
		List<Long> userIdList = new ArrayList<>();
		for(String id : idArray) {
			userIdList.add(Long.valueOf(id));
		}
		
		List<Follow> followList = followService.getFolloweeList(userId, userIdList);
		
		return new ResultDto(200, "OK", followList);
	}
}
