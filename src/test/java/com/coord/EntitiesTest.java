package com.coord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coord.service.NominatimFeignClient;

@SpringBootTest
public class EntitiesTest {
	
	@Autowired
	NominatimFeignClient client;
	
	public void createPlaceForTest() {
		
	}

	@Test
	public void placeFillTest() {
		
	}

}
