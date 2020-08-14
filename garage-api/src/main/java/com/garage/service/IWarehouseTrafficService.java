package com.garage.service;

import com.garage.document.WearhouseTraffic;
import com.garage.model.TrafficType;

import reactor.core.publisher.Mono;

public interface IWarehouseTrafficService {
	Mono<WearhouseTraffic> increaseCounter(TrafficType trafficType);
}
