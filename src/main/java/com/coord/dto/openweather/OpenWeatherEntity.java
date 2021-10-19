package com.coord.dto.openweather;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@JsonIgnoreProperties
@Data
public class OpenWeatherEntity {

	@JsonProperty("name")
	private String cityName;
	@JsonProperty("id")
	private Long cityId;
	@JsonProperty("timezone")
	private Long timezoneShiftUTC;
	@JsonProperty("dt")
	private Long dataTimeUTC;
	@JsonProperty("coord")
	private Coord cityCoordinates;
	@JsonProperty("weather")
	private List<Weather> weather;
	@JsonProperty("main")
	private Main main;
	@JsonProperty("wind")
	private Wind wind;
	@JsonProperty("clouds")
	private Clouds clouds;
	@JsonProperty("rain")
	private Rain rain;
	@JsonProperty("snow")
	private Snow snow;
	@JsonProperty("sys")
	private Sys sys;
	
}
