package com.coord.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "openatim", url = "${feign.nominatim.url}")
public class NominatimFeignClient {

}
