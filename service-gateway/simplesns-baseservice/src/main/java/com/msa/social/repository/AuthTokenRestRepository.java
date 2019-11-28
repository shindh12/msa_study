package com.msa.social.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.msa.domain.AuthToken;
import com.msa.social.repository.dto.AuthTokenData;
import com.msa.social.repository.dto.ResponseDto;

@Repository
public class AuthTokenRestRepository {

	@Autowired
	RestTemplate restTemplate; 
	
	public AuthToken getAuthToken(String token) {
		ResponseDto<AuthTokenData> response = restTemplate.
				exchange("http://localhost:8080/auth?token={token}", 
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<ResponseDto<AuthTokenData>>() {}, token).getBody();
		
		AuthTokenData authTokenData = response.getData();
		AuthToken authToken = new AuthToken(authTokenData.getToken(), authTokenData.getUserId());
		
		return authToken;
	}

}
