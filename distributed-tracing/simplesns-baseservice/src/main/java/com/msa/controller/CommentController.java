package com.msa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msa.controller.dto.CommentDto;
import com.msa.controller.dto.ResultDto;
import com.msa.domain.AuthToken;
import com.msa.domain.Comment;
import com.msa.service.AuthService;
import com.msa.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/comment")
	public ResultDto addComment(@RequestBody CommentDto dto, @CookieValue(value = "accesstoken", required = false) String token) {
		AuthToken authToken = authService.getAuthToken(token);
		
		if(authToken == null) {
			return new ResultDto(4002, "OK", "Authentication Failed");
		}
		
		
		Comment result = commentService.addComment(authToken.getUserId(), dto.getPostId(), dto.getContent());
		
		return new ResultDto(200, "Success", result);
	}
}
