package com.example.crm.service;

import java.util.List;
import java.util.Map;

import com.example.crm.dto.request.AddCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AddCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;

public interface CustomerService {

	CustomerResponse findById(String identity);

	List<CustomerResponse> findAll(int pageNo, int pageSize);

	AddCustomerResponse createCustomer(AddCustomerRequest request);

	UpdateCustomerResponse updateCustomer(String identity, UpdateCustomerRequest request);

	UpdateCustomerResponse patchCustomer(String identity, Map<String, Object> request);

	CustomerResponse removeById(String identity);

}