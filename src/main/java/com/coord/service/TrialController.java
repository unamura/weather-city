package com.coord.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.coord.dto.GeoCode;
import com.coord.dto.PlaceData;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TrialController {

	@Autowired
	private NominatimFeignClient clientFeign;

	@Autowired
	DataCoordinatesConverter mapper;
	
	@Autowired
	Map<Integer, PlaceData> placeMap;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to weather app";
	}

	@RequestMapping("/geo")
	public Map<Integer, PlaceData> getNominatimToPlaceData(
			@RequestParam(required = false, defaultValue = "Genova") String city) {
		GeoCode gc = clientFeign.retrieveGeoCode("geocodejson", city);
		placeMap = mapper.geoCodeToPlaceData(gc);

		return placeMap;
	}

	@RequestMapping("/geo/map")
	public PlaceData getPlaceList(@RequestParam Integer key) {
		PlaceData pd = mapper.getOnePlaceDataFromKey(key, placeMap);

		return pd;
	}
	
}
