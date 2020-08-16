package com.garage.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "wearhouseTraffic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseTraffic {
	@Id
	private String status;
	private int count;
}
