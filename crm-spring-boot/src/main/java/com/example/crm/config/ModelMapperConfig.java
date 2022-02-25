package com.example.crm.config;

import java.util.Objects;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.entity.Customer;

@Configuration
public class ModelMapperConfig {

	private static final Converter<UpdateCustomerRequest, 
	                                            Customer> 
	UPDATE_CUSTOMER_REQUEST_TO_CUSTOMER_CONVERTER = 
	(context) -> {
		var customer = new Customer();
		var updateCustReq = context.getSource();
		if (Objects.nonNull(updateCustReq.getPhoto()))
		   customer.setPhoto(
				   Base64.decodeBase64(
						   updateCustReq.getPhoto()));
       customer.setEmail(updateCustReq.getEmail());
       customer.setPhone(updateCustReq.getPhone());
       customer.setFullname(updateCustReq.getFullname());
       customer.setType(updateCustReq.getType());
	   return customer;
	};

	@Bean
	public ModelMapper mapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(UPDATE_CUSTOMER_REQUEST_TO_CUSTOMER_CONVERTER , 
				UpdateCustomerRequest.class, Customer.class );
		return mapper;
	}
}
