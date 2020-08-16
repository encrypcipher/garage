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
import com.garage.service.WarehouseService;

/**
 * Unit Testing the controller with a stand alone setup using MockMVC to mimic
 * an API call to the controller along with mock a service. Includes: One demo
 * test case
 */
@WebMvcTest(controllers = WarehouseController.class)
public class WarehouseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WarehouseService warehouseService;
	
	private TestMockApiData testMockApiData;
	private static final String TEST_PATH_WAREHOUSES = "/warehouses";
	
	@BeforeEach
	void init() throws IOException {
		testMockApiData = new TestMockApiData();
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	void getCarsTest() throws Exception {

		given(warehouseService.getWarehouseCars()).willReturn(testMockApiData.getWarehousesCars());
		MvcResult mvcResult = mockMvc.perform(get(TEST_PATH_WAREHOUSES))
				.andExpect(request().asyncStarted()).andDo(MockMvcResultHandlers.log()).andReturn();

		mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(1)));

		Mockito.verify(warehouseService, Mockito.times(1)).getWarehouseCars();
	}
}
