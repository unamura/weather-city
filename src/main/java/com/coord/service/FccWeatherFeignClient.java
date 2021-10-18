package com.coord.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coord.dto.FccWeatherEntity;

@FeignClient(value = "${feign.fccweather.name}", url = "${feign.fccweather.url}")
public interface FccWeatherFeignClient {

	@RequestMapping("/current")
	public FccWeatherEntity retrieveWeatherInfoFromPlace(
			@RequestParam(value = "lat", required = false) Double lat,
			@RequestParam(required = false) Double lon);
}
