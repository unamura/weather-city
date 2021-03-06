package com.coord.dto.fccweather;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@JsonIgnoreProperties
@Data
public class FccWeatherEntity {

	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "cod")
	private Integer cod;
	@JsonProperty(value = "timezone")
	private Long timezone;
	
}
