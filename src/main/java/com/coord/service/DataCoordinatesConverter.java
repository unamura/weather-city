package com.coord.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.coord.dto.PlaceData;
import com.coord.dto.PlaceWeather;
import com.coord.dto.DisplayPlaceData;
import com.coord.dto.PlaceCondition;
import com.coord.dto.nominatim.Features;
import com.coord.dto.nominatim.GeoCode;
import com.coord.dto.nominatim.Geometry;
import com.coord.dto.nominatim.Properties;
import com.coord.dto.openweather.Clouds;
import com.coord.dto.openweather.Coord;
import com.coord.dto.openweather.Main;
import com.coord.dto.openweather.OpenWeatherEntity;
import com.coord.dto.openweather.Rain;
import com.coord.dto.openweather.Snow;
import com.coord.dto.openweather.Weather;
import com.coord.dto.openweather.Wind;

@Component
public class DataCoordinatesConverter {

	@Value("${feign.openweather.key}")
	private String OPEN_WEATHER_API_KEY;
	private Double TO_CELSIUS = 273.15;

	@Autowired
	OpenWeatherFeignClient openWeatherClientFeign;

	public Map<Integer, PlaceData> geoCodeToPlaceData(GeoCode geoCode) {
		Integer index = 1;
		Map<Integer, PlaceData> placeList = new HashMap<Integer, PlaceData>();

		if (geoCode.getFeatures() != null) {

			for (Features feature : geoCode.getFeatures()) {
				PlaceData pData = new PlaceData();
				pData = featureToPlaceData(feature, pData);
				placeList.put(index, pData);
				index++;
			}
		}

		return placeList;
	}

	public PlaceData getOnePlaceDataFromKey(Integer key, Map<Integer, PlaceData> placeMap) {
		Integer size = placeMap.size();
		if (key != null && key <= size && key > 0) {
			return placeMap.get(key);
		}
		return null;
	}

	public OpenWeatherEntity getOpenWeatherEntityFromPlaceData(Double lon, Double lat) {
		if (lon != null && lat != null) {
			return openWeatherClientFeign.retrieveWeatherInfoFromPlace(lat, lon, OPEN_WEATHER_API_KEY);
		}
		return null;
	}

	public void openWeatherToDisplayDataMapper(OpenWeatherEntity ow, DisplayPlaceData dpd) {
		if (ow != null) {
			dpd.setPlaceName(ow.getCityName());
			if (ow.getDataTimeUTC() != null) {
				dpd.setDataCalculationDate(new Date(ow.getDataTimeUTC() * 1000));
			}
			if (ow.getSys() != null) {
				dpd.setCountryCode(ow.getSys().getCountryCode());
			}
			Coord owCoord = ow.getCityCoordinates();
			if (owCoord != null) {
				dpd.setPlaceLatitude(owCoord.getLat());
				dpd.setPlaceLongitude(owCoord.getLon());
			}
			Main owMain = ow.getMain();
			if (owMain != null) {
				if (owMain.getTempeKelvin() != null)
					dpd.setPlaceTempCelsius(owMain.getTempeKelvin() - TO_CELSIUS);
				if (owMain.getTempFeelsLikeKelvin() != null)
					dpd.setPlaceTempPerceived(owMain.getTempFeelsLikeKelvin() - TO_CELSIUS);
				if (owMain.getTempMaxKelvin() != null)
					dpd.setPlaceTempMaxCelsius(owMain.getTempMaxKelvin() - TO_CELSIUS);
				if (owMain.getTempMinKelvin() != null)
					dpd.setPlaceTempMinCelsius(owMain.getTempMinKelvin() - TO_CELSIUS);
				dpd.setPlaceHumidityPercentage(owMain.getHumidityPercentage());
			}
			List<Weather> owWeather = ow.getWeather();
			List<PlaceWeather> pwList = new ArrayList<PlaceWeather>();
			if (owWeather != null) {
				for (Weather w : owWeather) {
					PlaceWeather pw = new PlaceWeather();
					pw.setWeatherType(w.getGroupParameter());
					pw.setWeatherCondition(w.getGroupCondition());
					pwList.add(pw);
				}
				dpd.setPlaceWeatherList(pwList);
			}
			PlaceCondition pdpCond = new PlaceCondition();
			Wind owWind = ow.getWind();
			if (owWind != null) {
				pdpCond.setWindDirectionDegree(owWind.getWindDirectionDegree());
				pdpCond.setWindSpeedMeterSec(owWind.getWindSpeedMeterSec());
			}
			Clouds owCloud = ow.getClouds();
			if (owCloud != null)
				pdpCond.setCloudPercentage(owCloud.getCloudPercentage());
			Rain owRain = ow.getRain();
			if (owRain != null)
				pdpCond.setRainVolLastOneHourMM(owRain.getRainVolLastOneHourMM());
			Snow owSnow = ow.getSnow();
			if (owSnow != null)
				pdpCond.setSnowVolLastOneHourMM(owSnow.getSnowVolLastOneHourMM());
			dpd.setPlaceCondition(pdpCond);

		}

	}

	public PlaceData featureToPlaceData(Features feature, PlaceData pData) {
		Properties prop = feature.getProperties();
		Geometry geom = feature.getGeometry();
		if (prop != null) {
			if (prop.getGeocoding() != null) {
				pData.setPlaceName(prop.getGeocoding().getName());
				pData.setPlaceDetails(prop.getGeocoding().getLabel());
			}
		}
		if (geom != null) {
			if (geom.getCoordinates().size() == 2) {
				pData.setPlaceLongitude(geom.getCoordinates().get(0));
				pData.setPlaceLatitude(geom.getCoordinates().get(1));
			}
		}
		return pData;
	}

}
