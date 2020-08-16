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
	@NotNull
	private Integer id;
	
	@Min(1500)
	@NotNull
	private int year;
	
	@NotBlank(message = "Model is mandatory")
	private String model;
	
	@NotBlank(message = "Make is mandatory")
	private String make;
	
	@Min(1)
	@NotNull
	private BigDecimal price;
}
