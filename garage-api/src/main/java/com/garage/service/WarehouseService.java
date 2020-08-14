package com.garage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.dao.WarehouseDao;
import com.garage.model.Warehouse;

import reactor.core.publisher.Mono;
/**
 * Service layer class to perform business logic related to external API data
 *
 */
@Service
public class WarehouseService implements IWarehouseService{
	
	@Autowired
	private WarehouseDao warehouseDao;

	@Override
	public Mono<List<Warehouse>> getCars() {
		return warehouseDao.getWarehouses();
	}

}
