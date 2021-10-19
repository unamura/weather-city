package com.coord.dto.openweather;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties
public class Main {
	@JsonProperty("temp")
	private Double tempeKelvin;
	@JsonProperty("feels_like")
	private Double tempFeelsLikeKelvin;
	@JsonProperty("temp_min")
	private Double tempMinKelvin;
	@JsonProperty("temp_max")
	private Double tempMaxKelvin;
	@JsonProperty("humidity")
	private Integer humidityPercentage;
}
