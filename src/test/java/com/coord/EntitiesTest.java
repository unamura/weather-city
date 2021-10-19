package com.coord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.coord.dto.PlaceData;
import com.coord.dto.nominatim.Features;
import com.coord.dto.nominatim.GeoCode;
import com.coord.dto.nominatim.Geocoding;
import com.coord.dto.nominatim.Geometry;
import com.coord.dto.nominatim.Properties;
import com.coord.dto.openweather.Coord;
import com.coord.dto.openweather.Main;
import com.coord.dto.openweather.OpenWeatherEntity;
import com.coord.dto.openweather.Sys;
import com.coord.dto.openweather.Weather;
import com.coord.service.DataCoordinatesConverter;
import com.coord.service.NominatimFeignClient;
import com.coord.service.OpenWeatherFeignClient;

@SpringBootTest
public class EntitiesTest {
	private static final String PLACE = "rivarolo";
	private static final Double LAT = 44.437982;
	private static final Double LON = 8.8929743;
	@Value("${feign.openweather.key}")
	private String OPEN_WEATHER_API_KEY;

	@Autowired
	NominatimFeignClient nominatimClientFeign;

	@Autowired
	OpenWeatherFeignClient owClientFeign;

	@Autowired
	DataCoordinatesConverter mapper;

	public GeoCode createGeoCodeForTest() {
		GeoCode gc = nominatimClientFeign.retrieveGeoCode("geocodejson", PLACE);
		return gc;
	}
	
	public OpenWeatherEntity createOpenWeatherForTest() {
		OpenWeatherEntity ow = owClientFeign.retrieveWeatherInfoFromPlace(LAT, LON, OPEN_WEATHER_API_KEY);
		return ow;
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
	public void openWeatherEntityFillTest() {
		OpenWeatherEntity ow = createOpenWeatherForTest();
		assertNotNull(ow);
		assertNotNull(ow.getCityName());
		assertNotNull(ow.getCityId());
		assertNotNull(ow.getTimezoneShiftUTC());
		assertNotNull(ow.getDataTimeUTC());
		assertNotNull(ow.getCityCoordinates());
		Coord owCoord = ow.getCityCoordinates();
		assertNotNull(owCoord.getLat());
		assertNotNull(owCoord.getLon());
		assertNotNull(ow.getWeather());
		List<Weather> owWeather = ow.getWeather();
		for(Weather w : owWeather) {
			assertNotNull(w.getGroupCondition());
			assertNotNull(w.getGroupParameter());
		}
		assertNotNull(ow.getMain());
		Main owMain = ow.getMain();
		assertNotNull(owMain.getHumidityPercentage());
		assertNotNull(owMain.getTempeKelvin());
		assertNotNull(owMain.getTempFeelsLikeKelvin());
		assertNotNull(owMain.getTempMaxKelvin());
		assertNotNull(owMain.getTempMinKelvin());
		assertNotNull(ow.getSys());
		Sys owSys = ow.getSys();
		assertNotNull(owSys.getCountryCode());
		assertNotNull(owSys.getTimeSunriseUTC());
		assertNotNull(owSys.getTimeSunsetUTC());
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
