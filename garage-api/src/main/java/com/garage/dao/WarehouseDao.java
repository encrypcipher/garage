package com.garage.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.garage.model.TrafficType;
import com.garage.model.Warehouse;
import com.garage.service.WarehouseTrafficService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Dao layer to interact with data source: External API
 *
 */
@Repository
@Slf4j
public class WarehouseDao implements IWarehouseDao {
	
	@Autowired
	private WarehouseTrafficService warehouseTrafficService;
	private final WebClient webClient;
	private static final String EXTERNAL_API_PATH = "/b/5ebe673947a2266b1478d892";
	
	public WarehouseDao(@Value("${external-service-baseurl}") String baseURL) {
		this.webClient = WebClient.builder().baseUrl(baseURL).build();
	}

	public Mono<List<Warehouse>> getWarehouses() {
		return webClient.get().uri(EXTERNAL_API_PATH).exchange().flatMap(response -> {
			if (response.statusCode().is4xxClientError()) {
				Mono<String> errMsg = response.bodyToMono(String.class);
				return errMsg.flatMap(msg -> {
					log.error(msg);
					warehouseTrafficService.increaseCounter(TrafficType.BAD_REQUEST);
					return Mono.just(new ArrayList<>());
				});
			}
			;
			if (response.statusCode().is5xxServerError()) {
				Mono<String> errMsg = response.bodyToMono(String.class);
				return errMsg.flatMap(msg -> {
					log.error(msg);
					warehouseTrafficService.increaseCounter(TrafficType.ERROR);
					return Mono.just(new ArrayList<>());
				});
			}
			;
			if (response.statusCode().is2xxSuccessful()) {
				warehouseTrafficService.increaseCounter(TrafficType.SUCCESS);
			}
			;
			return response.bodyToMono(new ParameterizedTypeReference<List<Warehouse>>() {
			});
		});
	}
}
