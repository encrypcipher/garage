package com.garage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WarehouseVehicle {
	@JsonProperty("_id")
	private Integer id;
	@JsonProperty("make")
	private String make;
	@JsonProperty("model")
	private String model;
	@JsonProperty("year_model")
	private Integer yearModel;
	@JsonProperty("price")
	private Double price;
	@JsonProperty("licensed")
	private Boolean licensed;
	@JsonProperty("date_added")
	private String dateAdded;
}
