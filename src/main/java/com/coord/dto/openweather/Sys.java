package com.coord.dto.openweather;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties
public class Sys {
	@JsonProperty("country")
	private String countryCode;
	@JsonProperty("sunrise")
	private Long timeSunriseUTC;
	@JsonProperty("sunset")
	private Long timeSunsetUTC;
}
