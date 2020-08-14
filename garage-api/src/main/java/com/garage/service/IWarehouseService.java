package com.garage.service;

import java.util.List;

import com.garage.model.Warehouse;

import reactor.core.publisher.Mono;

public interface IWarehouseService {
	
	Mono<List<Warehouse>> getCars();
}
