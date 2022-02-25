package com.example.crm.service;

import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.dto.request.AddCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AddCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.entity.Customer;
import com.example.crm.repository.CustomerRepository;

@Service
public class CustomerService {
	private static final Supplier<IllegalArgumentException>
	      customerNotFoundExceptionSupplier = 
	      () -> new IllegalArgumentException("Cannot find the customer");
	private CustomerRepository customerRepository;
	private ModelMapper modelMapper;
	
	public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	public CustomerResponse findById(String identity) {
		return modelMapper.map(
			customerRepository.findById(identity)
	      .orElseThrow( customerNotFoundExceptionSupplier)
				 , CustomerResponse.class);
	}

	public List<CustomerResponse> findAll(int pageNo, int pageSize) {
		return customerRepository.findAll(PageRequest.of(pageNo, pageSize))
				.stream()
				.map( customer -> modelMapper.map(customer, CustomerResponse.class))
				.sorted(Comparator.comparing(CustomerResponse::getFullname))
				.toList();
	}

	@Transactional
	public AddCustomerResponse createCustomer(AddCustomerRequest request) {
		var customer = modelMapper.map(request, Customer.class);
		return modelMapper.map(customerRepository.save(customer), AddCustomerResponse.class);
	}

	public UpdateCustomerResponse updateCustomer(String identity, UpdateCustomerRequest request) {
		var customer = customerRepository.findById(identity)
				  .orElseThrow(customerNotFoundExceptionSupplier);

		
		return null;
	}

	public UpdateCustomerResponse patchCustomer(String identity, Map<String, Object> request) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerResponse removeById(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

}
