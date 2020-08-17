package com.mockapi.controller;

import java.io.File;
import java.nio.file.Files;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {
	
	@GetMapping("/garages")
	public JSONArray getCars() {
		try {
			File resource = new ClassPathResource("garages.json").getFile();
			String warehouses = new String(Files.readAllBytes(resource.toPath()));
			JSONParser parser = new JSONParser();
			return (JSONArray) parser.parse(warehouses);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}
