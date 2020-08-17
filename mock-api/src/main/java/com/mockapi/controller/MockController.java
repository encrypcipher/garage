package com.mockapi.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
//			File resource = new ClassPathResource("garages.json").getFile();
//			String warehouses = new String(Files.readAllBytes(resource.toPath()));
			InputStream resource = new ClassPathResource("garages.json").getInputStream();
			String warehouses;
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
				}
				warehouses = sb.toString().trim();
			}
			JSONParser parser = new JSONParser();
			return (JSONArray) parser.parse(warehouses);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}
