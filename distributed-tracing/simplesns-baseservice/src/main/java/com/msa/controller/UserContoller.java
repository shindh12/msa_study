package com.msa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.controller.dto.ResultDto;
import com.msa.controller.dto.UserDto;
import com.msa.domain.User;
import com.msa.service.UserService;

@RestController
public class UserContoller {

	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResultDto signUp(@RequestBody UserDto userDto) {		
		User newUser = userService.addUser(userDto.getUsername(), userDto.getPassword());
		
		return new ResultDto(200, "Success", newUser);
	}
	
	@GetMapping("/user")
	public ResultDto getUser(@RequestParam("id") Long id) {
		
		User user = userService.getUser(id);
		
		return new ResultDto(200, "Success", user);
	}
}
