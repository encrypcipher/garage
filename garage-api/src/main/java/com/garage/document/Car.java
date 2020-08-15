package com.garage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class Car {
	@Id
	private int id;
	private String year;
	private String model;
	private String make;
	private String price;
}
