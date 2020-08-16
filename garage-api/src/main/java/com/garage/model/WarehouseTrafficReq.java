package com.garage.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseTrafficReq {
	
	@NotBlank(message = "status is mandatory")
	private String Status;
	
	@NotBlank(message = "trafficCountType is mandatory")
	private TrafficCountType trafficCountType;
}
