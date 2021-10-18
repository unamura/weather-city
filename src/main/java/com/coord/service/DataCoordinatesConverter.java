package com.coord.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coord.dto.FccWeatherEntity;
import com.coord.dto.Features;
import com.coord.dto.GeoCode;
import com.coord.dto.Geometry;
import com.coord.dto.PlaceData;
import com.coord.dto.Properties;

@Component
public class DataCoordinatesConverter {
	
	@Autowired
	FccWeatherFeignClient fccWeatherClientFeign;

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
		if(key != null && key <= size && key > 0) {
			return placeMap.get(key);
		}
		return null;
	}

	public FccWeatherEntity getFccWeatherEntityFromPlaceData(Double lon, Double lat) {
		if(lon != null && lat != null) {
			return fccWeatherClientFeign.retrieveWeatherInfoFromPlace(lat, lon);
		}
		return null;
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
