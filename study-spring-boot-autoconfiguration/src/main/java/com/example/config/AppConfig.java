package com.example.config;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"test", "preprod", "prod"})
public class AppConfig {

	@Bean
	public Random createSecureRandom() {
		return new SecureRandom();
	}
}
