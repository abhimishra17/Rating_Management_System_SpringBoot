package com.springBootproject.RatingManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvgReportDto {
    private double ambiance;
    private double food;
    private double service;
    private double cleanliness;
    private double drinks;
    private double overall;
}
