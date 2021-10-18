package com.coord.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.coord.dto.Features;
import com.coord.dto.GeoCode;
import com.coord.dto.Geometry;
import com.coord.dto.PlaceData;
import com.coord.dto.Properties;

@Component
public class DataCoordinatesConverter {

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