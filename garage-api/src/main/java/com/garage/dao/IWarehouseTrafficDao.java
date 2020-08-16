package com.garage.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.garage.entity.WarehouseTraffic;

import reactor.core.publisher.Mono;

@Repository
public interface IWarehouseTrafficDao extends ReactiveMongoRepository<WarehouseTraffic, String> {

	@Query("{ 'status': ?0 }")
	Mono<WarehouseTraffic> findByStatus(final String status);
}
