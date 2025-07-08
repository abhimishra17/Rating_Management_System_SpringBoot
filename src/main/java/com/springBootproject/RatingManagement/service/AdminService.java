package com.springBootproject.RatingManagement.service;

import com.springBootproject.RatingManagement.dto.AvgReportDto;
import com.springBootproject.RatingManagement.dto.FilterRequest;
import com.springBootproject.RatingManagement.entity.Rating;
import com.springBootproject.RatingManagement.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

//    to fetch all rating records from the database
    private final RatingRepository ratingRepository;

    public List<Rating> filterRatings(FilterRequest request) {
        List<Rating> ratings = ratingRepository.findAll();

        return ratings.stream()
                .filter(r -> (request.getAmbiance() == null || r.getAmbiance() == request.getAmbiance())||(request.getFood() == null || r.getFood() == request.getFood())||(request.getService() == null || r.getService() == request.getService()))
//                .filter(r -> request.getFood() == null || r.getFood() == request.getFood())
//                .filter(r -> request.getService() == null || r.getService() == request.getService())
//                .filter(r -> request.getCleanliness() == null || r.getCleanliness() == request.getCleanliness())
//                .filter(r -> request.getDrinks() == null || r.getDrinks() == request.getDrinks())
                .collect(Collectors.toList());
    }

    public AvgReportDto getAverageReport() {
        List<Rating> ratings = ratingRepository.findAll();

        if (ratings.isEmpty()) {
            return new AvgReportDto(0, 0, 0, 0, 0, 0);
        }

        double ambianceAvg = ratings.stream().mapToInt(Rating::getAmbiance).average().orElse(0);
        double foodAvg = ratings.stream().mapToInt(Rating::getFood).average().orElse(0);
        double serviceAvg = ratings.stream().mapToInt(Rating::getService).average().orElse(0);
        double cleanlinessAvg = ratings.stream().mapToInt(Rating::getCleanliness).average().orElse(0);
        double drinksAvg = ratings.stream().mapToInt(Rating::getDrinks).average().orElse(0);

        double overallAvg = (ambianceAvg + foodAvg + serviceAvg + cleanlinessAvg + drinksAvg) / 5;

        return new AvgReportDto(ambianceAvg, foodAvg, serviceAvg, cleanlinessAvg, drinksAvg, overallAvg);
    }
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}

