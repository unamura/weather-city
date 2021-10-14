package com.coord.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Component
@JsonIgnoreProperties
@Data
public class Features {
	private Geometry geometry;
	private Properties properties;
}
