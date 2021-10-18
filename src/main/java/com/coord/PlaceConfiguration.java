package com.coord;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coord.dto.PlaceData;

@Configuration
public class PlaceConfiguration {

	@Bean
	public Map<Integer, PlaceData> placeMap() {
		return new HashMap<Integer, PlaceData> ();
	}
}
