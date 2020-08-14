package com.garage.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.garage.document.WearhouseTraffic;
import com.garage.model.TrafficType;

@Repository
public interface IWarehouseTrafficDao extends ReactiveMongoRepository<WearhouseTraffic, Integer> {
	
	@Query("{ 'tafficType': ?0 }")
	Optional<WearhouseTraffic>  findByTrafficType(final TrafficType trafficType);
}
