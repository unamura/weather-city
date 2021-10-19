package com.coord.dto.openweather;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties
public class Wind {
	@JsonProperty("wind")
	private Double windSpeedMeterSec;
	@JsonProperty("deg")
	private Double windDirectionDegree;
}
