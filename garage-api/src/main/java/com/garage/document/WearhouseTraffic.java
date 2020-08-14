package com.garage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.garage.model.TrafficType;

import lombok.Data;
import lombok.NoArgsConstructor;
@Document
@Data  
@NoArgsConstructor
public class WearhouseTraffic {
	@Id
	TrafficType trafficType;
	int count;
}
