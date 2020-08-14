package com.garage.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class WarehouseAssortment {
	@JsonProperty("location")
	private String location;
	@JsonProperty("vehicles")
	private List<WarehouseVehicle> vehicles ;
}
