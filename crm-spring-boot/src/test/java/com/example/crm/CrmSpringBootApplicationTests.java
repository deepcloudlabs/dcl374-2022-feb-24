package com.example.crm;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.service.CustomerService;

@SpringBootTest(
	classes = CrmSpringBootApplication.class,
	webEnvironment = WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class CrmSpringBootApplicationTests {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CustomerService customerService;
	
	@DisplayName("")
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

}
