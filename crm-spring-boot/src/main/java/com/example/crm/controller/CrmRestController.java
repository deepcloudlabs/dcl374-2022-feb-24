package com.example.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.dto.request.AddCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AddCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.service.CustomerService;
import com.example.validation.TcKimlikNo;

@RestController
@RequestScope
@RequestMapping("/customers")
@Validated 
public class CrmRestController {

	private CustomerService customerService;
	
	public CrmRestController(CustomerService customerService) {
		this.customerService = customerService;
	}

	// localhost:9100/crm/api/v1/customers/11111111110
	@GetMapping("{identity}")
	public CustomerResponse getCustomerByIdentity(
	  @PathVariable @TcKimlikNo String identity) {
		return customerService.findById(identity);
	}
	
	@GetMapping
	public List<CustomerResponse> getCustomersByPage(
		@RequestParam(required = false, defaultValue = "0") 
		int pageNo, 
		@RequestParam(required = false, defaultValue = "20")  
		int pageSize){
		  return customerService.findAll(pageNo,pageSize);
	}
	
	@PostMapping
	public AddCustomerResponse addCustomer(
		@RequestBody @Validated AddCustomerRequest request) {
		return customerService.createCustomer(request);
	}
	
	@PutMapping("{identity}")
	public UpdateCustomerResponse updateCustomer(
		@PathVariable @TcKimlikNo String identity,	
		@RequestBody @Validated UpdateCustomerRequest request) {
		return customerService.updateCustomer(identity,request);
	}
	
	@PatchMapping("{identity}")
	public UpdateCustomerResponse patchCustomer(
			@PathVariable @TcKimlikNo String identity,	
			@RequestBody Map<String,Object> request) {
		return customerService.patchCustomer(identity,request);
	}
	
	@GetMapping("{identity}")
	public CustomerResponse deleteCustomerByIdentity(
	  @PathVariable @TcKimlikNo String identity) {
		return customerService.removeById(identity);
	}
}
