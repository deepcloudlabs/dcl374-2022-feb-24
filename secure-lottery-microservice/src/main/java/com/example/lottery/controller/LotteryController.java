package com.example.lottery.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.service.LotteryService;

@RestController
@RequestMapping("/numbers")
@RequestScope
@CrossOrigin
@Validated
public class LotteryController {
	private LotteryService lotteryService;
	
	public LotteryController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@GetMapping(params={"column", "max", "size"})
	public List<List<Integer>> getLotteryNumbers(
	   @RequestParam @Min(50) int max,
	   @RequestParam @Min(6) int size,
	   @RequestParam @Min(5) @Max(100) int column
	){
		return lotteryService.draw(max, size, column);
	}
}
