package com.garage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.jdi.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

	@JsonProperty("_id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("cars")
	private WarehouseAssortment cars;
}
