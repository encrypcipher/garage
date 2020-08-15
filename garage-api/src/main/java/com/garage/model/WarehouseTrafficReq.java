package com.garage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseTrafficReq {
	private String Status;
	private TrafficCountType trafficCountType;
}
