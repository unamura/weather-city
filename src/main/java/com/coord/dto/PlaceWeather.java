package com.coord.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PlaceWeather {
	private String weatherType;
	private String weatherCondition;
}
