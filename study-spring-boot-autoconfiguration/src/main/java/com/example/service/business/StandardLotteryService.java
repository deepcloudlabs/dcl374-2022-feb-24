package com.example.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.service.LotteryService;
import com.example.service.RandomNumberGenerator;

@Service
public class StandardLotteryService implements LotteryService {
	private RandomNumberGenerator randomNumberGenerator;
	
	public StandardLotteryService(
			//@Qualifier("secureRandomNumberGenerator")
			RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	@Override
	public List<Integer> draw(int max, int size) {
		return IntStream.generate( 
				() -> randomNumberGenerator.generate(1, max))
				        .distinct()
				        .limit(size)
				        .sorted()
				        .boxed()
				        .toList();
	}

}
