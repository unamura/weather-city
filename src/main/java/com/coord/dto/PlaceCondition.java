package com.coord.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PlaceCondition {
	private Double windSpeedMeterSec;
	private Double windDirectionDegree;
	private String cloudPercentage;
	private Long rainVolLastOneHourMM;
	private Long snowVolLastOneHourMM;
}
