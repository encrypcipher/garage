package com.garage.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	private BigDecimal price;
	@JsonProperty("licensed")
	private Boolean licensed;
	@JsonProperty("date_added")
	private String dateAdded;
}
