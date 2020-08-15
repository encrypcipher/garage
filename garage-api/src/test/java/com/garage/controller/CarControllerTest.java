package com.garage.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.garage.data.TestMockApiData;
import com.garage.service.CarService;
import com.garage.service.WarehouseService;

/**
 * Unit Testing the controller with a stand alone setup using MockMVC to mimic
 * an API call to the controller along with mock a service. Includes: One demo
 * test case
 */
@WebMvcTest(controllers = CarController.class)
public class CarControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WarehouseService warehouseService;
	
	@MockBean
	private CarService carService;
	
	private TestMockApiData testMockApiData;
	private static final String TEST_ENDPOINT_CAR = "/garage/api/v1/cars";
	
	@BeforeEach
	void init() throws IOException {
		testMockApiData = new TestMockApiData();
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	void getCarsTest() throws Exception {

		given(warehouseService.getCars()).willReturn(testMockApiData.getCars());
		MvcResult mvcResult = mockMvc.perform(get(TEST_ENDPOINT_CAR))
				.andExpect(request().asyncStarted()).andDo(MockMvcResultHandlers.log()).andReturn();

		mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(testMockApiData.getCars().block().size())));

		Mockito.verify(warehouseService, Mockito.times(1)).getCars();
	}
	
}
