package com.garage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garage.entity.Car;
import com.garage.exception.GarageApiException;
import com.garage.service.WarehouseService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Controller layer to handle HTTP requests
 *
 */
@Slf4j
@RestController
public class WarehouseController {
	
	private static final String PATH_WAREHOUSE = "/warehouses";
	
	@Autowired
	private WarehouseService warehouseService;

	@GetMapping(PATH_WAREHOUSE)
	public Mono<List<Car>> getWarehouseCars() {
		try {
			return warehouseService.getWarehouseCars();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new GarageApiException(ex.getMessage(), ex);
		}
	}

}
