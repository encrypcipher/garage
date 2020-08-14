package com.garage.dao;

import java.util.List;

import com.garage.model.Warehouse;

import reactor.core.publisher.Mono;

public interface IWarehouseDao {
	
	Mono<List<Warehouse>> getWarehouses();
}
