package com.garage.service;

import java.util.List;

import com.garage.entity.Car;

import reactor.core.publisher.Mono;

public interface IWarehouseService {
	
	Mono<List<Car>> getWarehouseCars();
}
