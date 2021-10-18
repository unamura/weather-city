package com.coord.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${feign.openweather.name}", url = "${feign.openweather.url}")
public interface OpenWeatherFeignClient {
	static final String appid = "f4b84caaa15a14077daa494326b1163e";
	Double lat = 44.3036653;
	Double lon = 9.2093446;

	@RequestMapping("/weather")
	public void retrieveWeatherInfoFromPlace(@RequestParam Double lat, @RequestParam Double lon,
			@RequestParam String appid);

}
