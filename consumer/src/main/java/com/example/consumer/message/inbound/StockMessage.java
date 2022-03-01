package com.example.consumer.message.inbound;

public record StockMessage(
	String symbol,
	double price,
	double quantity,
	Side side) {}
