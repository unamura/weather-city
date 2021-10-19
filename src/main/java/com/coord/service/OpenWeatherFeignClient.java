package com.coord.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coord.dto.openweather.OpenWeatherEntity;

@FeignClient(value = "${feign.openweather.name}", url = "${feign.openweather.url}")
public interface OpenWeatherFeignClient {

	@RequestMapping("/weather")
	public OpenWeatherEntity retrieveWeatherInfoFromPlace(@RequestParam(value = "lat", required = false) Double lat,
			@RequestParam(value = "lon", required = false) Double lon, 
			@RequestParam(value = "appid", required = false) String appId);
}
