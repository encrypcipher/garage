package com.garage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garage.data.TestMockApiData;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import reactor.test.StepVerifier;

/**
 * Integration test using a mock server mimicking a real HTTP call
 *
 */
public class WarehouseDaoTest {

	private TestMockApiData testMockApiData;
	private WarehouseDao warehouseDao;
	private MockWebServer mockWebServer;
	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void init() throws IOException {
		testMockApiData = new TestMockApiData();
		 this.mockWebServer = new MockWebServer();
		 this.mockWebServer.start();
		 warehouseDao = new WarehouseDao(mockWebServer.url("/").toString());
	}
	
	@AfterEach
	void tearDown() throws IOException {
		this.mockWebServer.shutdown();
	}

	@Test
	public void test2GetAllGithubRepositories() throws Exception {
		//Mock the call and add a response
		mockWebServer.enqueue(new MockResponse()
	    	      .setBody(mapper.writeValueAsString(testMockApiData.getMockApiData()))
	    	      .addHeader("Content-Type", "application/json"));

		// Asserting response
		StepVerifier.create(warehouseDao.getWarehouses()).assertNext(res -> {
			assertNotNull(res);
			assertEquals(1, res.size());
			assertEquals("id1", res.get(0).getId());
		}).verifyComplete();
		
		RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/b/5ebe673947a2266b1478d892", recordedRequest.getPath());
	}
}
