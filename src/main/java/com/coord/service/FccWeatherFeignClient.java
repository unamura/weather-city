package com.coord.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coord.dto.FccWeatherEntity;

@FeignClient(value = "${feign.fccweather.name}", url = "${feign.fccweather.url}")
public interface FccWeatherFeignClient {
	String lat = "44.3036653";
	String lon = "9.2093446";

	@RequestMapping("/current")
	public FccWeatherEntity retrieveWeatherInfoFromPlace(
			@RequestParam(value = "lat", required = false, defaultValue = lat) String lat,
			@RequestParam(required = false, defaultValue = lon) String lon);
}
