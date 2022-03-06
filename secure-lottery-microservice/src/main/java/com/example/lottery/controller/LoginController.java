package com.example.lottery.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.dto.request.WebUser;
import com.example.lottery.service.JwtTokenProvider;

@RestController
@RequestScope
@RequestMapping("/login")
@CrossOrigin
@Validated
public class LoginController {
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	
	public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PostMapping
	public String login(@RequestBody @Validated WebUser webUser) {
		try {
			UsernamePasswordAuthenticationToken authToken = 
					new UsernamePasswordAuthenticationToken(
					   webUser.getUsername(), webUser.getPassword());
			authenticationManager.authenticate(authToken);
			return jwtTokenProvider.createToken(webUser.getUsername());
		} catch (Exception e) {
			System.err.println("Log in failed for user " + webUser.getUsername());
		}
		return "";		
	}
}
