package com.example.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.service.RandomNumberGenerator;

@Service
//@Primary
@Profile("dev")
public class FastRandomNumberGenerator implements RandomNumberGenerator {
	
	public FastRandomNumberGenerator() {
		System.err.println("FastRandomNumberGenerator::FastRandomNumberGenerator");
	}

	@Override
	public int generate(int min, int max) {
		return ThreadLocalRandom.current()
				          .nextInt(min, max);
	}

}
