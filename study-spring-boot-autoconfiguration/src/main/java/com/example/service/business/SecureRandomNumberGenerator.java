package com.example.service.business;

import java.util.Random;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.service.RandomNumberGenerator;

@Service
@Profile({"test", "preprod", "prod"})
public class SecureRandomNumberGenerator implements RandomNumberGenerator {
	private Random random;

	public SecureRandomNumberGenerator(Random random) {
		System.err.println("SecureRandomNumberGenerator::SecureRandomNumberGenerator");
		this.random = random;
	}

	@Override
	public int generate(int min, int max) {
		return random.nextInt(min, max);
	}

}
