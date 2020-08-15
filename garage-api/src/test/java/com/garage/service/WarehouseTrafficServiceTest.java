package com.garage.service;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.garage.dao.IWarehouseTrafficDao;
import com.garage.data.TestMockApiData;
import com.garage.document.WarehouseTraffic;
import com.garage.model.StatusConstants;

/**
 * Demo: Unit testing service class in isolation
 *
 */
@ExtendWith(MockitoExtension.class)
public class WarehouseTrafficServiceTest {
	
	@InjectMocks
	private  WarehouseTrafficService  warehouseTrafficService;
	
	@Mock
	private IWarehouseTrafficDao  warehouseTrafficDao;

	@Mock
	private StatusConstants statusConstants;
	
	private TestMockApiData testMockApiData;
	
	@BeforeEach
	void init() throws IOException {
		testMockApiData = new TestMockApiData();
	}
	
	@Test
	public void increaseCounterTest() {
		when(warehouseTrafficDao.findByStatus(Mockito.anyString())).thenReturn(testMockApiData.getTraffic());
		when(warehouseTrafficDao.save(Mockito.any(WarehouseTraffic.class))).thenReturn(testMockApiData.getTraffic());
		warehouseTrafficService.increaseCounter("TOTAL");
		Mockito.verify(warehouseTrafficDao, Mockito.times(2)).save(Mockito.any(WarehouseTraffic.class));
	}
}
