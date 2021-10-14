package com.coord.dto;

import java.util.List;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties
public class Geometry {

	private String type;	
	private List<Double> coordinates;

}
