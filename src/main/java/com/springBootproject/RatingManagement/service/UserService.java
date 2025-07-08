package com.springBootproject.RatingManagement.service;

import com.springBootproject.RatingManagement.dto.RatingRequest;
import com.springBootproject.RatingManagement.entity.Rating;
import com.springBootproject.RatingManagement.entity.User;
import com.springBootproject.RatingManagement.repository.RatingRepository;
import com.springBootproject.RatingManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    public void rate(String email, RatingRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));


        // Check if the user has already submitted a rating
//        if (ratingRepository.existsByUser(user)) {
//            throw new RuntimeException("You have already submitted your rating.");
//        }

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setAmbiance(request.getAmbiance());
        rating.setFood(request.getFood());
        rating.setService(request.getService());
        rating.setCleanliness(request.getCleanliness());
        rating.setDrinks(request.getDrinks());

        ratingRepository.save(rating);
    }
}


