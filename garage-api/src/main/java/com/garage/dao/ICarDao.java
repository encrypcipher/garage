package com.garage.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.garage.document.Car;
/**
 * Dao layer to interact with data source: mongo DB
 *
 */
@Repository
public interface ICarDao extends ReactiveMongoRepository<Car, Integer> {
     
}
