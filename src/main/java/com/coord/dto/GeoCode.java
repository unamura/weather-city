package com.coord.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties
public class GeoCode {
	@JsonProperty("type")
	private String type;
	private List<Features> features;
	private String aInstructions;

}
