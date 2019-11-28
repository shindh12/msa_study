package com.msa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msa.domain.User;
import com.msa.service.UserService;

@Controller
public class UIController {

	@Autowired
	UserService userService;
	
	@RequestMapping({"", "/"})
	public String getIndex(@CookieValue(value = "accesstoken", required = false) String token, Model model) {
		User user = null;
		if(token != null) {
			user = userService.getUserByToken(token);
		}
		
		model.addAttribute("user", user);
		return "index";
	}
	
	@RequestMapping("/login")
	public String getLogin(@CookieValue(value = "accesstoken", required = false) String token, Model model) {
		
		return "login";
	}
	
	@RequestMapping("/signup")
	public String getSignUp(@CookieValue(value = "accesstoken", required = false) String token, Model model) {
		
		return "signup";
	}
	
	@RequestMapping(value="/post/detail/{id}")
	public String getIndexPage(@PathVariable("id") Long id, @CookieValue(value = "accesstoken", required = false) String token, Model model) {
		User user = null;
		if(token != null) {
			user = userService.getUserByToken(token);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("id", id);
		return "detail";
	}
}
