package com.coord.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.coord.dto.GeoCode;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TrialController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to app";
	}

	@RequestMapping("/geo/{city}")
	public GeoCode getFromGoogle(@PathVariable(required = true) String city) {
		log.info("Initializing getFromGoogle method");
		String url = "https://nominatim.openstreetmap.org/search?format=geocodejson&city=" + city;
		RestTemplate rt = new RestTemplate();
		GeoCode gc = rt.getForObject(url, GeoCode.class);
		
		return gc;
	}

}
