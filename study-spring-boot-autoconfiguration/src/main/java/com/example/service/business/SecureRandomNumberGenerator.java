package com.example.service.business;

import java.util.Random;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.service.RandomNumberGenerator;

@Service
//@Profile({"test", "preprod", "prod"})
@ConditionalOnProperty(
	value = "random-service", 
	havingValue = "secure"
)
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
