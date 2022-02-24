package com.example.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.LotteryService;

@Controller
@Scope("request")
@RequestMapping("numbers")
public class LotteryRestController {

	private LotteryService lotteryService;

	public LotteryRestController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}
	
	// http://localhost:8100/lottery/api/v1/numbers?max=60&size=6&column=10
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<List<Integer>> getLotteryNumbers(
			@RequestParam int max,
			@RequestParam int size,
			@RequestParam int column){
		return lotteryService.draw(max,size,column);
	}
}
