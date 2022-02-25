package com.example.crm.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.entity.Customer;

@Configuration
public class ModelMapperConfig {
//	private static final Converter<Customer, CustomerResponse> 
//	CUSTOMER_TO_CUSTOMER_RESPONSE_CONVERTER = (context) -> {
//		var response = new CustomerResponse();
//		var customer = context.getSource();
//		response.setIdentity(customer.getIdentity());
//		
//		return response;
//	};

	@Bean
	public ModelMapper mapper() {
		var mapper = new ModelMapper();
//		mapper.addConverter(CUSTOMER_TO_CUSTOMER_RESPONSE_CONVERTER , Customer.class, CustomerResponse.class );
		return mapper;
	}
}
