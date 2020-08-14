package com.garage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.garage.model.Warehouse;

import reactor.core.publisher.Mono;
/**
 * Dao layer to interact with data source: External API 
 *
 */
@Repository
public class WarehouseDao implements IWarehouseDao {

	private final WebClient webClient;
	private static final String EXTERNAL_API_PATH = "/b/5ebe673947a2266b1478d892";

	public WarehouseDao(@Value("${external-service-baseurl}") String baseURL) {
		this.webClient = WebClient.builder().baseUrl(baseURL).build();
	}

	public Mono<List<Warehouse>> getWarehouses() {
		return webClient.get().uri(EXTERNAL_API_PATH).exchange()
				.flatMap(response -> response.bodyToMono(new ParameterizedTypeReference<List<Warehouse>>() {
				}));
	}
}
