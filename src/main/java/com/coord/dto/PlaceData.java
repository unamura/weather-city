package com.coord.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
public class PlaceData {

	private String placeName;
	private String placeDetails;
	//TODO validare i campi
	// Coordinate da non essere nulle
	@NotBlank
	private Double placeLongitude;
	@NotBlank
	private Double placeLatitude;
}
