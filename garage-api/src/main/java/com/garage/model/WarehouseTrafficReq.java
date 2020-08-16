package com.garage.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseTrafficReq {
	
	@NotBlank(message = "status: property is mandatory")
	private String Status;
	
	@NotBlank(message = "trafficCountType: property is mandatory")
	private TrafficCountType trafficCountType;
}
