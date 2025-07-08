//package com.springBootproject.RatingManagement.service;
//
//import com.springBootproject.RatingManagement.dto.RatingRequest;
//import com.springBootproject.RatingManagement.entity.Rating;
//import com.springBootproject.RatingManagement.entity.User;
//import com.springBootproject.RatingManagement.repository.RatingRepository;
//import com.springBootproject.RatingManagement.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class RatingService {
//
//    private final RatingRepository ratingRepository;
//    private final UserRepository userRepository;
//
//    public void rate(String email, RatingRequest request) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Prevent multiple ratings by the same user
////        if (ratingRepository.existsByUser(user)) {
////            throw new RuntimeException("You have already submitted your rating.");
////        }
//
//        Rating rating = new Rating();
//        rating.setUser(user);
//        rating.setAmbiance(request.getAmbiance());
//        rating.setFood(request.getFood());
//        rating.setService(request.getService());
//        rating.setCleanliness(request.getCleanliness());
//        rating.setDrinks(request.getDrinks());
//
//        ratingRepository.save(rating);
//    }
//}


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
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    public void rate(String email, RatingRequest request) {
        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Prevent duplicate ratings from the same user
        // if (ratingRepository.existsByUser(user)) {
        //     throw new RuntimeException("You have already submitted your rating.");
        // }

        // Create and populate Rating object
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setAmbiance(request.getAmbiance());
        rating.setFood(request.getFood());
        rating.setService(request.getService());
        rating.setCleanliness(request.getCleanliness());
        rating.setDrinks(request.getDrinks());

        // Save rating to the database
        ratingRepository.save(rating);
    }
}
