package com.msa.service;

import com.msa.domain.AuthToken;

public interface AuthService {
	
	public AuthToken generateAuthToken(String username, String password);
	
	public AuthToken getAuthToken(String token);
	
	public void deleteAuthToken(Long userId);
}
