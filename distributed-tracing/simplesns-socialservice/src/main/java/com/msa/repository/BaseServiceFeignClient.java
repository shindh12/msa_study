package com.msa.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.msa.repository.dto.AuthTokenData;
import com.msa.repository.dto.ResponseDto;

@FeignClient("baseservice")
public interface BaseServiceFeignClient {
	@RequestMapping(
			method=RequestMethod.GET,
			value="/auth",
			consumes="application/json")
	ResponseDto<AuthTokenData> getAuthToken(@RequestParam(value="token") String token);
}