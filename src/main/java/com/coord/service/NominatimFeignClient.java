package com.coord.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coord.dto.nominatim.GeoCode;

@FeignClient(value = "${feign.nominatim.name}", url = "${feign.nominatim.url}")
public interface NominatimFeignClient {

	@RequestMapping("/search")
	public GeoCode retrieveGeoCode(@RequestParam String format, 
			@RequestParam String city);

}
