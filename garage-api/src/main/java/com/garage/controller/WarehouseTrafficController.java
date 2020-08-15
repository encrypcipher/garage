package com.garage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garage.exception.GarageApiException;
import com.garage.model.WarehouseTrafficReq;
import com.garage.service.WarehouseTrafficService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Controller to handle traffic related HTTP requests
 *
 */
@Slf4j
@RestController
@RequestMapping("/garage/api/v1")
public class WarehouseTrafficController {

	@Autowired
	private WarehouseTrafficService warehouseTrafficService;

	private static final String PATH_TRAFFIC = "/traffic";

	@GetMapping(PATH_TRAFFIC)
	public Mono<Integer> getTrafficResults(@RequestBody WarehouseTrafficReq warehouseTrafficReq) {
		try {
			return warehouseTrafficService.claculateCount(warehouseTrafficReq);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new GarageApiException(ex.getMessage(), ex);
		}
	}
}
