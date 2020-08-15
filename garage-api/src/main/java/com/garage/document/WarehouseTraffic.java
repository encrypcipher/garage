package com.garage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
@Document(value = "wearhouseTraffic")
@Data  
@NoArgsConstructor
public class WarehouseTraffic {
	@Id
	private String status;
	private int count;
}
