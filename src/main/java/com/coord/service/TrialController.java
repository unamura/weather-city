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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to weather app";
	}

	@RequestMapping("/geo/{city}")
	public GeoCode getFromNominatim(@PathVariable(required = true) String city) {
		log.info("Initializing getFromGoogle method");
		String url = "https://nominatim.openstreetmap.org/search?format=geocodejson&city=" + city;
		RestTemplate rt = new RestTemplate();
		GeoCode gc = rt.getForObject(url, GeoCode.class);
		String ins = "Per scegliere una città andare alla URL .../id \n Dove id è il numero della città scelta.";
		gc.setAInstructions(ins);

		return gc;
	}

	@RequestMapping("/geo")
	public Map<Integer, PlaceData> getFromNominatimFeign(
			@RequestParam(required = false, defaultValue = "Genova") String city) {
		GeoCode gc = clientFeign.retrieveGeoCode("geocodejson", city);
		Map<Integer, PlaceData> placeList = mapper.geoCodeToPlaceData(gc);

		return placeList;
	}

}
