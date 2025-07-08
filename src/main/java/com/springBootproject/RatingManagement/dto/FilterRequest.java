package com.springBootproject.RatingManagement.dto;

import lombok.Data;

    @Data
    public class FilterRequest {
        private Integer ambiance;
        private Integer food;
        private Integer service;
        private Integer cleanliness;
        private Integer drinks;
    }


