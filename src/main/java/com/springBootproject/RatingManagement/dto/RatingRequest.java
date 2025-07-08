package com.springBootproject.RatingManagement.dto;

import lombok.Data;

@Data
public class RatingRequest {
    private int ambiance;
    private int food;
    private int service;
    private int cleanliness;
    private int drinks;
}

