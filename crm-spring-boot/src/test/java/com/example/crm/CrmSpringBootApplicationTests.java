package com.example.crm;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.crm.dto.request.AddCustomerRequest;
import com.example.crm.dto.response.AddCustomerResponse;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.exception.CustomerNotFoundException;
import com.example.crm.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(
	classes = CrmSpringBootApplication.class,
	webEnvironment = WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class CrmSpringBootApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	CustomerService customerService;
	
	@DisplayName("get request with identity should return status ok")
	@ParameterizedTest
	@CsvFileSource(resources = "customers.csv")
	void getCustomerByIdentityShoudlReturnOk(
			String identity,
			String fullname,
			String email,
			String phone) throws Throwable {
		// 1. Test Setup
		var customerResponse = new CustomerResponse();
		customerResponse.setIdentity(identity);
		customerResponse.setFullname(fullname);
		customerResponse.setEmail(email);
		customerResponse.setPhone(phone);
		Mockito.when(customerService.findById(identity))
		       .thenReturn(customerResponse);
		// 2. Call exercise method
        mockMvc.perform(
        	get("/customers/"+identity)
            .accept(MediaType.APPLICATION_JSON)
        )
        // 3. Verification
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.identity",is(identity)))
        .andExpect(jsonPath("$.fullname",is(fullname)))
        .andExpect(jsonPath("$.email",is(email)))
        .andExpect(jsonPath("$.phone",is(phone)));
		// 4. Tear-down
	}
	
	@DisplayName("get request with identity should return status ok")
	@ParameterizedTest
	@CsvFileSource(resources = "customers.csv")
	void getCustomerByIdentityShoudlReturnNotFound(
			String identity,
			String fullname,
			String email,
			String phone) throws Throwable {
		// 1. Test Setup
		var customerNotFoundException = 
				new CustomerNotFoundException("Customer not found",
				1000,
				"b9a0ffd4-add0-43b1-b448-ba1ee48b7ce5");
		Mockito.when(customerService.findById(identity))
		       .thenThrow(customerNotFoundException);
		// 2. Call exercise method
		mockMvc.perform(
				get("/customers/"+identity)
				.accept(MediaType.APPLICATION_JSON)
				)
		// 3. Verification
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.debugId",is("b9a0ffd4-add0-43b1-b448-ba1ee48b7ce5")))
        .andExpect(jsonPath("$.i18nId",is(1000)));		
		// 4. Tear-down
	}
	
	@Test
	void getCustomersAtFirstPageShoudlReturnOk() throws Throwable {
		// 1. Test Setup
		var customer1 = new CustomerResponse();
		customer1.setIdentity("11111111110");
		customer1.setFullname("Jack Bauer");
		customer1.setEmail("jack@example.com");
		customer1.setPhone("+90 542 555 5555");
		var customer2 = new CustomerResponse();
		customer2.setIdentity("92558957066");
		customer2.setFullname("Kate Austen");
		customer2.setEmail("kate@example.com");
		customer2.setPhone("+90 542 666 6666");
		Mockito.when(customerService.findAll(0,2))
		       .thenReturn(List.of(customer1,customer2));
		// 2. Call exercise method
		mockMvc.perform(
				get("/customers?pageNo=0&pageSize=2")
				.accept(MediaType.APPLICATION_JSON)
				)
		// 3. Verification
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",hasSize(2)))
		.andExpect(jsonPath("$.length()",is(2)))
		.andExpect(jsonPath("$[0].identity",is("11111111110")))
		.andExpect(jsonPath("$[1].identity",is("92558957066")));
		// 4. Tear-down
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "customers.csv")
	void addCustomerShoudlReturnOk(
			String identity,
			String fullname,
			String email,
			String phone) throws Throwable {
		// 1. Test Setup
		var request = new AddCustomerRequest();
		request.setIdentity(identity);
		request.setFullname(fullname);
		request.setEmail(email);
		request.setPhone(phone);
		var response = modelMapper.map(request,
				AddCustomerResponse.class);
		Mockito.when(customerService.createCustomer(request))
		       .thenReturn(response);
		// 2. Call exercise method
		mockMvc.perform(
				post("/customers")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
		)
		// 3. Verification
		.andExpect(status().isOk())
        .andExpect(jsonPath("$.identity",is(identity)))
        .andExpect(jsonPath("$.fullname",is(fullname)))
        .andExpect(jsonPath("$.email",is(email)))
        .andExpect(jsonPath("$.phone",is(phone)));
		// 4. Tear-down
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "customers.csv")
	void removeCustomerByIdentityShoudlReturnOk(
			String identity,
			String fullname,
			String email,
			String phone) throws Throwable {
		// 1. Test Setup
		var customerResponse = new CustomerResponse();
		customerResponse.setIdentity(identity);
		customerResponse.setFullname(fullname);
		customerResponse.setEmail(email);
		customerResponse.setPhone(phone);
		Mockito.when(customerService.removeById(identity))
		       .thenReturn(customerResponse);
		// 2. Call exercise method
        mockMvc.perform(
        	delete("/customers/"+identity).accept(MediaType.APPLICATION_JSON)
        )
        // 3. Verification
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.identity",is(identity)))
        .andExpect(jsonPath("$.fullname",is(fullname)))
        .andExpect(jsonPath("$.email",is(email)))
        .andExpect(jsonPath("$.phone",is(phone)));
		// 4. Tear-down
	}

}
