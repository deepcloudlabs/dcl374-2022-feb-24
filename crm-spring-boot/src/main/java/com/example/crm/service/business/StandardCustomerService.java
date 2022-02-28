package com.example.crm.service.business;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.dto.request.AddCustomerRequest;
import com.example.crm.dto.request.UpdateCustomerRequest;
import com.example.crm.dto.response.AddCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.entity.Customer;
import com.example.crm.exception.CustomerNotFoundException;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.service.CustomerService;

@Service
@ConditionalOnProperty(
		name = "database", havingValue = "mysql"
)
public class StandardCustomerService implements CustomerService {
	private static final Supplier<CustomerNotFoundException>
	      customerNotFoundExceptionSupplier = 
	      () -> new CustomerNotFoundException(
	    		  "Cannot find the customer",
	    		  1000, 
	    		  "821b0947-4758-49da-a0f6-a08fad807016"
	       );
	private CustomerRepository customerRepository;
	private ModelMapper modelMapper;
	
	public StandardCustomerService(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CustomerResponse findById(String identity) {
		return modelMapper.map(
			customerRepository.findById(identity)
	      .orElseThrow( customerNotFoundExceptionSupplier)
				 , CustomerResponse.class);
	}

	@Override
	public List<CustomerResponse> findAll(int pageNo, int pageSize) {
		return customerRepository.findAll(PageRequest.of(pageNo, pageSize))
				.stream()
				.map( customer -> modelMapper.map(customer, CustomerResponse.class))
				.sorted(Comparator.comparing(CustomerResponse::getFullname))
				.toList();
	}

	@Override
	@Transactional
	public AddCustomerResponse createCustomer(AddCustomerRequest request) {
		var customer = modelMapper.map(request, Customer.class);
		return modelMapper.map(customerRepository.save(customer), AddCustomerResponse.class);
	}

	@Override
	@Transactional
	public UpdateCustomerResponse updateCustomer(String identity, UpdateCustomerRequest request) {
		var customer = customerRepository.findById(identity)
		.orElseThrow(customerNotFoundExceptionSupplier);
	     modelMapper.map(request,customer);	  
	    // customerRepository.flush();
	    return modelMapper.map(
	    		customerRepository.saveAndFlush(customer),
	    		//customer,
	    		 UpdateCustomerResponse.class);
	}

	@Override
	@Transactional
	public UpdateCustomerResponse patchCustomer(String identity, Map<String, Object> request) {
		var customer = customerRepository.findById(identity)
		                                 .orElseThrow(customerNotFoundExceptionSupplier);
	     request.forEach((property, value) -> {
	    	 Field declaredField;
			try {
				declaredField = Customer.class.getDeclaredField(property);
				if (property.equals("phone")) {
					declaredField.setAccessible(true);
					declaredField.set(customer, value.toString());
					declaredField.setAccessible(false);
				}
			} catch (Exception e) {
			}
	     });	     
		return modelMapper.map(
	    		customerRepository.save(customer), 
	    		 UpdateCustomerResponse.class);
	}

	@Override
	@Transactional
	public CustomerResponse removeById(String identity) {
		var customer = customerRepository.findById(identity)
                .orElseThrow(customerNotFoundExceptionSupplier);
		customerRepository.deleteById(identity);
        return modelMapper.map(customer,CustomerResponse.class); 
	}

}
