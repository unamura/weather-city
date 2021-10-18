package com.coord.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Geocoding {
	private String label;
	private String name;
}
