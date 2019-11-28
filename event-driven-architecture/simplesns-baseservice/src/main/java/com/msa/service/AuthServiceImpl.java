package com.msa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.domain.AuthToken;
import com.msa.domain.User;
import com.msa.repository.AuthTokenRepository;
import com.msa.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AuthTokenRepository authTokenRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public AuthToken generateAuthToken(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		
		if(user == null) {
			return null;
		}
		
		String seed = String.valueOf(user.getId()) + user.getUsername() + String.valueOf(System.currentTimeMillis());
		AuthToken authToken = new AuthToken(user.getId(), seed);
		authTokenRepository.saveAndFlush(authToken);
		
		return authToken;
	}

	@Override
	public AuthToken getAuthToken(String token) {
		Optional<AuthToken> result = authTokenRepository.findById(token);
		AuthToken authToken = null;
		
		if(result.isPresent()) {
			authToken = result.get();
		}
		
		return authToken;
	}

	// bad unused
	@Override
	public void deleteAuthToken(Long userId) {
		authTokenRepository.deleteByUserId(userId);
	}
	
	

}
