package com.garage.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.garage.dao.WarehouseDao;
import com.garage.data.TestMockApiData;
import com.garage.model.StatusConstants;

import reactor.test.StepVerifier;

/**
 * Demo: Unit testing service class in isolation
 *
 */
@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {
	
	@InjectMocks
	private  WarehouseService  warehouseService;
	
	@Mock
	private WarehouseDao WarehouseDao;
	
	@Mock
	private WarehouseTrafficService warehouseTrafficService;

	@Mock
	private StatusConstants statusConstants;
	
	private TestMockApiData testMockApiData;
	
	@BeforeEach
	void init() throws IOException {
		testMockApiData = new TestMockApiData();
	}
	
	@Test
	public void getCarsTest() throws Exception {
		doNothing().when(warehouseTrafficService).increaseCounter(Mockito.anyString());
		when(statusConstants.getTotal()).thenReturn("Total");
		when(statusConstants.getSuccess()).thenReturn("success");
		when(WarehouseDao.getWarehouses()).thenReturn(testMockApiData.getCars());
		// Asserting response
				StepVerifier.create(warehouseService.getCars()).assertNext(res -> {
					assertNotNull(res);
					assertEquals(1, res.size());
					assertEquals("id1", res.get(0).getId());
				}).verifyComplete();
	}
}
