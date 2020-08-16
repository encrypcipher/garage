package com.garage.service;

import com.garage.entity.Car;

import reactor.core.publisher.Mono;

public interface ICarService {
	
	Mono<Car> create(Car e);

    Mono<Car> update(Car e);
 
    Mono<Void> delete(Integer id);
}
