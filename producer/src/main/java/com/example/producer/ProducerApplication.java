package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.producer.message.outbound.Side;
import com.example.producer.message.outbound.StockMessage;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication {
	@Autowired
	private ApplicationEventPublisher aep;
	
	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
	
	@Scheduled(fixedRate = 1_000)
	public void sendOrder() {
		aep.publishEvent(new StockMessage("orcl",100,100,Side.ASK));
	}

}
