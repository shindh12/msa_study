package com.msa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msa.controller.dto.ResultDto;
import com.msa.controller.dto.UserDto;
import com.msa.domain.AuthToken;
import com.msa.service.AuthService;

@RestController
public class AuthController {

	@Autowired
	AuthService authService; 
	
	@PostMapping("/auth")
	public ResultDto createAuthToken(@RequestBody UserDto dto) {
		
		AuthToken authToken = authService.generateAuthToken(dto.getUsername(), dto.getPassword());
		
		if(authToken == null) {
			return new ResultDto(4001, "Success", "No Data");	
		}
		
		return new ResultDto(200, "Success", authToken);
	}
	
	@GetMapping("/auth")
	public ResultDto getAuthToken(@RequestParam String token) {
		AuthToken authToken = authService.getAuthToken(token);
		
		return new ResultDto(200, "OK", authToken);
	}
}
