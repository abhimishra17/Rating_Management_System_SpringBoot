package com.springBootproject.RatingManagement.controller;

import com.springBootproject.RatingManagement.dto.RatingRequest;
import com.springBootproject.RatingManagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/rate")
    public ResponseEntity<String> rateService(
            @RequestBody RatingRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String email = userDetails.getUsername();
        userService.rate(email, request);
        return ResponseEntity.ok("Rating submitted successfully");
    }
}


