package com.example.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.producer.message.outbound.StockMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaProducerService {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	@Value("${order.topic}")
	private String topic;
	
	@EventListener
	public void sendOrderThroughKafka(StockMessage message) 
			throws Throwable {
		kafkaTemplate.send(topic, 
				objectMapper.writeValueAsString(message));
	}
}
