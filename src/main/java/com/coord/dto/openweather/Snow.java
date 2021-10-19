package com.coord.dto.openweather;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Component
@Data
@JsonIgnoreProperties
public class Snow {
	@JsonProperty("1h")
	private Long snowVolLastOneHourMM;
	@JsonProperty("3h")
	private Long snowVolLastThreeHoursMM;
}
