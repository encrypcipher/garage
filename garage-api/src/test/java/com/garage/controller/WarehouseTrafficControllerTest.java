package com.garage.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garage.model.TrafficCountType;
import com.garage.model.WarehouseTrafficReq;
import com.garage.service.WarehouseTrafficService;

import reactor.core.publisher.Mono;

/**
 * Unit Testing the controller with a stand alone setup using MockMVC to mimic
 * an API call to the controller along with mock a service. Includes: One demo
 * test case
 */
@WebMvcTest(controllers = WarehouseTrafficController.class)
public class WarehouseTrafficControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WarehouseTrafficService warehouseTrafficService;
	
	private static final String TEST_ENDPOINT_TRAFFIC = "/garage/api/v1/traffic";

	@Test
	public void getCountTest() throws Exception {

		when(warehouseTrafficService.claculateCount(Mockito.any(WarehouseTrafficReq.class))).thenReturn(Mono.just(1));
		MvcResult mvcResult = mockMvc.perform(get(TEST_ENDPOINT_TRAFFIC).content(reqJson()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(request().asyncStarted()).andDo(MockMvcResultHandlers.log()).andReturn();

		mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(Mono.just(1).block())));

		Mockito.verify(warehouseTrafficService, Mockito.times(1)).claculateCount(Mockito.any(WarehouseTrafficReq.class));
	}
	
	public String reqJson() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(new WarehouseTrafficReq("4XX", TrafficCountType.MAX));
		
	}
	
}

