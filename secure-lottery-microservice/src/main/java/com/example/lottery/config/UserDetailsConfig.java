package com.example.lottery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsConfig {
	@Bean
	protected UserDetailsService userDetailsService() {
		var jack = User.withUsername("jack").password("{noop}secret").roles("USER").build();
		var kate = User.withUsername("kate").password("{noop}secret").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(jack, kate);
	}
}
