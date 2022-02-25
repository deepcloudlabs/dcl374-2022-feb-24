package com.example.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.crm.dto.request.AddCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AddCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;

@Service
public class CustomerService {

	public CustomerResponse findById(String identity) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CustomerResponse> findAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public AddCustomerResponse createCustomer(AddCustomerRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public UpdateCustomerResponse updateCustomer(String identity, UpdateCustomerRequest request) {
		// TODO Auto-generated method stub
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
