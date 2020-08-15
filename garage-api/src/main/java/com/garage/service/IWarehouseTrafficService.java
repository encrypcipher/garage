package com.garage.service;

import com.garage.model.WarehouseTrafficReq;

import reactor.core.publisher.Mono;

public interface IWarehouseTrafficService {

	void increaseCounter(String status);
	Mono<Integer> claculateCount(WarehouseTrafficReq warehouseTrafficReq);
}
