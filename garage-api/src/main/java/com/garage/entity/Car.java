package com.garage.entity;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	
	@Id
	@Min(1)
	@NotNull(message = "id: property is mandatory")
	private Integer id;
	
	@Min(1500)
	@NotNull(message = "year: property is mandatory")
	private int year;
	
	@NotBlank(message = "model: property is mandatory")
	private String model;
	
	@NotBlank(message = "make: property is mandatory")
	private String make;
	
	@Min(1)
	@NotNull(message = "price: property is mandatory")
	private BigDecimal price;
}
