package com.garage.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.garage.entity.Car;
import com.garage.entity.WarehouseTraffic;
import com.garage.model.Warehouse;
import com.garage.model.WarehouseAssortment;
import com.garage.model.WarehouseVehicle;

import reactor.core.publisher.Mono;

/**
 * Demo: Test Data to be used for the entire tests
 *
 */
public class TestMockApiData {
	
	public List<Warehouse> getMockWarehouseData() {
		List<Warehouse> warehouseList = new ArrayList<>();
		WarehouseAssortment cars = new WarehouseAssortment();
		List<WarehouseVehicle> vehicles = new ArrayList<>();
		vehicles.add(new WarehouseVehicle(999, "Aurora", "infinity", 2006, new BigDecimal(43210), true, "2017-8-12"));
		cars.setVehicles(vehicles);
		warehouseList.add(new Warehouse("id1", "Warehouse1", null, cars));
		return warehouseList;
	}
	
	public Mono<List<Warehouse>> getWarehouses() {
		return Mono.just(this.getMockWarehouseData());
	}
	
	public List<Car> getMockWarehouseCarsData() {
		List<Car> carList = new ArrayList<>();
		carList.add(new Car(321,1992,"swift","suzuki",new BigDecimal(21999)));
		return carList;
	}
	
	public Mono<List<Car>> getWarehousesCars() {
		return Mono.just(this.getMockWarehouseCarsData());
	}
	
	public Mono<Car> getMockCarData(){
		return Mono.just(new Car(123,2002,"Mustang","Cheverlot",new BigDecimal(21999)));
	}
	
	
	public Mono<WarehouseTraffic> getTraffic(){
		return Mono.just(new WarehouseTraffic("200", 2));
	}
	
	public Mono<WarehouseTraffic> getEmptyTraffic(){
		return Mono.empty();
	}
}
