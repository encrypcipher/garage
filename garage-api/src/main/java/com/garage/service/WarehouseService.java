package com.garage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.garage.dao.WarehouseDao;
import com.garage.exception.GarageApiException;
import com.garage.model.StatusConstants;
import com.garage.model.Warehouse;

import reactor.core.publisher.Mono;

/**
 * Service layer class to perform business logic related to external API data
 *
 */
@Service
public class WarehouseService implements IWarehouseService {

	@Autowired
	private WarehouseDao warehouseDao;

	@Autowired
	private WarehouseTrafficService warehouseTrafficService;

	@Autowired
	private StatusConstants statusConstants;

	@Override
	public Mono<List<Warehouse>> getWarehouses() {
		return warehouseDao.getWarehouses().doOnSuccess(onSuccess -> {
			warehouseTrafficService.increaseCounter(statusConstants.getSuccess());
			warehouseTrafficService.increaseCounter(statusConstants.getTotal());
		}).doOnError(GarageApiException.class, e -> {
			if (e.getMessage().equals(HttpStatus.BAD_REQUEST.toString())) {
				warehouseTrafficService.increaseCounter(statusConstants.getTotal());
				warehouseTrafficService.increaseCounter(statusConstants.getBadRequest());
			}
			if (e.getMessage().equals(HttpStatus.INTERNAL_SERVER_ERROR.toString())) {
				warehouseTrafficService.increaseCounter(statusConstants.getTotal());
				warehouseTrafficService.increaseCounter(statusConstants.getSeerverError());
			}
		});
	}
}
