package com.garage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garage.dao.ICarDao;
import com.garage.document.Car;

import reactor.core.publisher.Mono;

/**
 * Service layer class to perform business logic related to Car entity
 *
 */
@Service
public class CarService implements ICarService{
	
	@Autowired
	private ICarDao carDao;
	
	@Override
	public  Mono<Car> create(Car car) {
		return carDao.save(car);
	}

	@Override
	public Mono<Car> update(Car car) {
		return carDao.save(car);
	}

	@Override
	public Mono<Void> delete(Integer id) {
		return carDao.deleteById(id);
	}

}
