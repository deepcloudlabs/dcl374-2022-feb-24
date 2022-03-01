package com.example.producer.message.outbound;

public record StockMessage(
	String symbol,
	double price,
	double quantity,
	Side side) {}
