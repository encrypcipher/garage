package com.garage.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.garage.data.TestMockApiData;
import com.garage.entity.Car;
import com.garage.service.CarService;

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
	private CarService carService;
	
	private TestMockApiData testMockApiData;
	private static final String TEST_ENDPOINT_CAR = "/garage/api/v1/cars";
	
	@BeforeEach
	void init() throws IOException {
		testMockApiData = new TestMockApiData();
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = "USER")
	void assCarTest() throws Exception {
		Car car = new Car(123,2002,"Mustang","Cheverlot",new BigDecimal(21999));
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson=ow.writeValueAsString(car);
		given(carService.create(Mockito.any(Car.class))).willReturn(testMockApiData.getMockCarData());
		MvcResult mvcResult = mockMvc.perform(post(TEST_ENDPOINT_CAR).contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(request().asyncStarted()).andDo(MockMvcResultHandlers.log()).andReturn();

		mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(123)))
			    .andExpect(jsonPath("$.year", is(2002)))	
			    .andExpect(jsonPath("$.model", is("Mustang")));

		Mockito.verify(carService, Mockito.times(1)).create(Mockito.any(Car.class));
	}
	
}
