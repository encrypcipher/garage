package com.garage.document;

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
	@NotBlank(message = "Year is mandatory")
	private String year;
	@NotBlank(message = "Model is mandatory")
	private String model;
	@NotBlank(message = "Make is mandatory")
	private String make;
	@Min(1)
	@NotNull
	private Integer price;
}
