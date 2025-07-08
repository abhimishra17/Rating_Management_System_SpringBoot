package com.springBootproject.RatingManagement.controller;

import com.springBootproject.RatingManagement.dto.RatingRequest;
import com.springBootproject.RatingManagement.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitRating(
            @RequestBody RatingRequest ratingRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();
        ratingService.rate(email, ratingRequest);
        return ResponseEntity.ok("Rating submitted successfully.");
    }
}




