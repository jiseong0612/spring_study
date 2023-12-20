package com.makeBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.makeBlog.domain.AddUserRequest;
import com.makeBlog.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserApiController {
	private final UserService userService;
	
	@PostMapping("/user")
	public String signUp(AddUserRequest request) {
		userService.save(request);
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler()
		.logout(
				request
				, response
				, SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login";
	}
}
