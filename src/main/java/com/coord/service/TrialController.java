package com.coord.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coord.dto.PlaceData;
import com.coord.dto.DisplayPlaceData;
import com.coord.dto.nominatim.GeoCode;
import com.coord.dto.openweather.OpenWeatherEntity;

@CrossOrigin
@RestController
public class TrialController {

	@Autowired
	private NominatimFeignClient nominatimClientFeign;
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

	//@CrossOrigin
	@RequestMapping("/geo/map")
	public PlaceData getPlaceList(@RequestParam Integer key) {
		placeData = mapper.getOnePlaceDataFromKey(key, placeMap);

		return placeData;
	}

	@RequestMapping("/geo/map/weather")
	public DisplayPlaceData getOpenWeather() {
		Double lon = placeData.getPlaceLongitude();
		Double lat = placeData.getPlaceLatitude();		
		OpenWeatherEntity owe = mapper.getOpenWeatherEntityFromPlaceData(lon, lat);
		DisplayPlaceData wd = new DisplayPlaceData();
		mapper.openWeatherToDisplayDataMapper(owe, wd);

		return wd;
	}

}
