package com.garage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.garage.dao.WarehouseDao;
import com.garage.entity.Car;
import com.garage.exception.GarageApiException;
import com.garage.model.StatusConstants;

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
	public Mono<List<Car>> getWarehouseCars() {
		return warehouseDao.getWarehouses().doOnSuccess(onSuccess -> {
			warehouseTrafficService.increaseCounter(statusConstants.getSuccess());
		}).doOnError(GarageApiException.class, e -> {
			if (e.getMessage().equals(HttpStatus.BAD_REQUEST.toString())) {
				warehouseTrafficService.increaseCounter(statusConstants.getBadRequest());
			}
			if (e.getMessage().equals(HttpStatus.INTERNAL_SERVER_ERROR.toString())) {
				warehouseTrafficService.increaseCounter(statusConstants.getSeerverError());
			}
		}).doFinally(onFinally -> {
			warehouseTrafficService.increaseCounter(statusConstants.getTotal());
		}).switchIfEmpty(Mono.just(new ArrayList<>())).map(warehouses -> {
			List<Car> cars = new ArrayList<>();
			warehouses.parallelStream().forEach((warehouse -> {
				if (warehouse.getCars() != null && warehouse.getCars().getVehicles() != null) {
					warehouse.getCars().getVehicles().parallelStream().forEach(vehicle -> {
						cars.add(new Car(vehicle.getId(), vehicle.getYearModel(), vehicle.getModel(), vehicle.getMake(),
								vehicle.getPrice()));
					});
				}

			}));
			return cars;
		});
	}
}
