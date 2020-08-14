package com.garage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WarehouseLocation {
	@JsonProperty("lat")
	private String lat;
	@JsonProperty("long")
	private String _long;
}
