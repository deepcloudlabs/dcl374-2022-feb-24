package com.example.lottery.dto.request;

import javax.validation.constraints.NotBlank;

public class WebUser {
	@NotBlank
	private String username;
	@NotBlank
	private String password;

	public WebUser() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
