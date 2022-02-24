package com.example.service;

import java.util.List;
import java.util.stream.IntStream;

@FunctionalInterface
public interface LotteryService {
	List<Integer> draw(int max, int size);
	default List<List<Integer>> draw(int max, int size,int column){
		return IntStream.range(0, column)
				        .mapToObj(i -> draw(max, size))
				        .toList();
	}
}
