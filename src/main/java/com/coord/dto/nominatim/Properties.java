package com.coord.dto.nominatim;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties
public class Properties {
	private Geocoding geocoding;
}