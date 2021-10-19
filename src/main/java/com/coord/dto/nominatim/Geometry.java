package com.coord.dto.nominatim;

import java.util.List;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties
public class Geometry {

	private String type;	
	// 0 Long 1 Latit
	private List<Double> coordinates;

}
