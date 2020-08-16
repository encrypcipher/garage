package com.garage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garage.exception.GarageApiException;
import com.garage.model.Warehouse;
import com.garage.service.WarehouseService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Controller layer to handle HTTP requests
 *
 */
@Slf4j
@RestController
@RequestMapping("/garage/api/v1")
public class WarehouseController {
	
	private static final String PATH_WAREHOUSE = "/warehouse";
	
	@Autowired
	private WarehouseService warehouseService;

	@GetMapping(PATH_WAREHOUSE)
	public Mono<List<Warehouse>> getCars() {
		try {
			return warehouseService.getWarehouses();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new GarageApiException(ex.getMessage(), ex);
		}
	}

}
