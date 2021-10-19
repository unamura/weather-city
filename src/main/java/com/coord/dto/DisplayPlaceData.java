package com.coord.dto;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class DisplayPlaceData {
	private String placeName;
	private String countryCode;
	private Date dataCalculationDate;
	private Double placeLongitude;
	private Double placeLatitude;
	private Double placeTempCelsius;
	private Double placeTempPerceived;
	private Double placeTempMinCelsius;
	private Double placeTempMaxCelsius;
	private Integer placeHumidityPercentage;
	private List<PlaceWeather> placeWeatherList;
	private PlaceCondition placeCondition;
}
