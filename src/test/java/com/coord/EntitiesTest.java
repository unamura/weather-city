package com.coord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coord.dto.Features;
import com.coord.dto.GeoCode;
import com.coord.dto.Geocoding;
import com.coord.dto.Geometry;
import com.coord.dto.PlaceData;
import com.coord.dto.Properties;
import com.coord.service.DataCoordinatesConverter;
import com.coord.service.NominatimFeignClient;

@SpringBootTest
public class EntitiesTest {
	private static final String PLACE = "rivarolo";

	@Autowired
	NominatimFeignClient nominatimClientFeign;
	
	@Autowired
	DataCoordinatesConverter mapper;

	public GeoCode createGeoCodeForTest() {
		GeoCode gc = nominatimClientFeign.retrieveGeoCode("geocodejson", PLACE);
		return gc;
	}

	@Test
	public void geoCodeFillTest() {
		GeoCode gc = createGeoCodeForTest();
		assertNotNull(gc);
		assertNotNull(gc.getFeatures());
		Properties ftProp = new Properties();
		Geometry ftGeom = new Geometry();
		for(Features ft : gc.getFeatures()) {
			assertNotNull(ft.getProperties());
			ftProp = ft.getProperties();
			assertNotNull(ft.getGeometry());
			ftGeom = ft.getGeometry();
		}
		assertNotNull(ftProp.getGeocoding());
		Geocoding ftPropGeoc = ftProp.getGeocoding();
		assertNotNull(ftPropGeoc);
		assertNotNull(ftPropGeoc.getName());
		assertNotNull(ftPropGeoc.getLabel());
		assertNotNull(ftGeom.getCoordinates());
		for(Double cd : ftGeom.getCoordinates()) {
			assertNotNull(cd);
		}
	}

	@Test
	public void placeDataMapperFillTest() {
		GeoCode gc = createGeoCodeForTest();
		Map<Integer, PlaceData> placeList = mapper.geoCodeToPlaceData(gc);
		for(Integer key : placeList.keySet()) {
			assertNotNull(placeList.get(key));
		}
	}
	
	@Test
	public void geoCodeNameSearchResult() {
		GeoCode gc = createGeoCodeForTest();
		Properties ftProp = new Properties();
		for(Features ft : gc.getFeatures()) {
			ftProp = ft.getProperties();
			String name = ftProp.getGeocoding().getName();
			assertEquals(PLACE, name.toLowerCase());
		}
	}

}
