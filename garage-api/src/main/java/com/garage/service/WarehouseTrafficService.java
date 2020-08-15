package com.garage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.garage.dao.IWarehouseTrafficDao;
import com.garage.document.WarehouseTraffic;
import com.garage.exception.GarageApiException;
import com.garage.model.WarehouseTrafficReq;

import reactor.core.publisher.Mono;

/**
 * Service class to handle business logic for website traffic
 *
 */
@Service
public class WarehouseTrafficService implements IWarehouseTrafficService {

	@Autowired
	private IWarehouseTrafficDao warehouseTrafficDao;
	
	private static final int COUNT_ONE = 1;
	private static final int COUNT_ZERO = 0;

	@Override
	public void increaseCounter(String status) {
		Mono<WarehouseTraffic> traffic = warehouseTrafficDao.findByStatus(status);
		traffic.flatMap(wearhouseTraffic -> {
			wearhouseTraffic.setCount((wearhouseTraffic.getCount() + 1));
			return warehouseTrafficDao.save(wearhouseTraffic);
		}).switchIfEmpty(initialTrafficSetUp(status)).subscribe();
	}

	@Override
	public Mono<Integer> claculateCount(WarehouseTrafficReq warehouseTrafficReq) {
		switch (warehouseTrafficReq.getTrafficCountType()) {
		case MIN:
			return getMinCount(warehouseTrafficReq.getStatus());
		case MAX:
			return getMaxCount(warehouseTrafficReq.getStatus());
		case AVERAGE:
			return getAvgCount(warehouseTrafficReq.getStatus());
		default:
			throw new GarageApiException(HttpStatus.BAD_REQUEST.toString());
		}
	}

	private Mono<WarehouseTraffic> initialTrafficSetUp(String status) {
		WarehouseTraffic w = new WarehouseTraffic();
		w.setStatus(status);
		w.setCount(COUNT_ONE);
		return warehouseTrafficDao.save(w);
	}

	private Mono<Integer> getMinCount(String status) {
		Mono<WarehouseTraffic> traffic = warehouseTrafficDao.findByStatus(status);
		return traffic.flatMap(input -> {
			return Mono.just(COUNT_ONE);
		}).switchIfEmpty(Mono.just(COUNT_ZERO));
	}

	private Mono<Integer> getMaxCount(String status) {
		Mono<WarehouseTraffic> traffic = warehouseTrafficDao.findByStatus(status);
		return traffic.flatMap(input -> {
			return Mono.just(input.getCount());
		}).switchIfEmpty(Mono.just(COUNT_ZERO));
	}

	private Mono<Integer> getAvgCount(String status) {
		Mono<WarehouseTraffic> traffic = warehouseTrafficDao.findByStatus(status);
		return traffic.flatMap(input -> {
			return Mono.just(input.getCount() / 2);
		}).switchIfEmpty(Mono.just(COUNT_ZERO));
	}

}
