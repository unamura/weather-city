package com.coord.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.coord.dto.GeoCode;
import com.coord.dto.FccWeatherEntity;
import com.coord.dto.PlaceData;

@RestController
public class TrialController {

	@Autowired
	private NominatimFeignClient nominatimClientFeign;
	@Autowired
	private FccWeatherFeignClient fccWeatherClientFeign;
	@Autowired
	DataCoordinatesConverter mapper;
	@Autowired
	PlaceData placeData;

	@Autowired
	Map<Integer, PlaceData> placeMap;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to weather app";
	}

	@RequestMapping("/geo")
	public Map<Integer, PlaceData> getNominatimToPlaceData(
			@RequestParam(required = false, defaultValue = "Genova") String city) {
		GeoCode gc = nominatimClientFeign.retrieveGeoCode("geocodejson", city);
		placeMap = mapper.geoCodeToPlaceData(gc);

		return placeMap;
	}

	@RequestMapping("/geo/map")
	public PlaceData getPlaceList(@RequestParam Integer key) {
		placeData = mapper.getOnePlaceDataFromKey(key, placeMap);

		return placeData;
	}

	@RequestMapping("/weath")
	public FccWeatherEntity getOpenWeather() {
		FccWeatherEntity owe = fccWeatherClientFeign.retrieveWeatherInfoFromPlace("44.3036653", "9.2093446");

		return owe;
	}

}
