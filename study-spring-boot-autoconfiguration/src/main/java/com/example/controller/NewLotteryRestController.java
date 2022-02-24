package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("numbers2")
public class NewLotteryRestController {

	private LotteryService lotteryService;

	public NewLotteryRestController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}
	
	// http://localhost:8100/lottery/api/v1/numbers?max=60&size=6&column=10
	@GetMapping
	public List<List<Integer>> getLotteryNumbers(
			@RequestParam int max,
			@RequestParam int size,
			@RequestParam int column){
		return lotteryService.draw(max,size,column);
	}
}
