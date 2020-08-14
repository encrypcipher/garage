package com.garage.data;

import java.util.ArrayList;
import java.util.List;

import com.garage.model.Warehouse;
import reactor.core.publisher.Mono;

/**
 * Test Data to be used for the entire tests
 *
 */
public class TestMockApiData {
	
	public List<Warehouse> getMockApiData() {
		List<Warehouse> warehouseList = new ArrayList<>();
		warehouseList.add(new Warehouse("id1", "Warehouse1", null, null));
		return warehouseList;
	}
	
	public Mono<List<Warehouse>> getCars() {
		return Mono.just(this.getMockApiData());
	}
}
