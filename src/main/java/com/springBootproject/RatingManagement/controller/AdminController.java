package com.springBootproject.RatingManagement.controller;

import com.springBootproject.RatingManagement.dto.AvgReportDto;
import com.springBootproject.RatingManagement.dto.FilterRequest;
import com.springBootproject.RatingManagement.entity.Rating;
import com.springBootproject.RatingManagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/filter")
    public ResponseEntity<List<Rating>> filterRatings(@RequestBody FilterRequest request) {
        return ResponseEntity.ok(adminService.filterRatings(request));
    }

    @GetMapping("/report")
    public ResponseEntity<AvgReportDto> getReport() {
        return ResponseEntity.ok(adminService.getAverageReport());
    }
    @GetMapping("/all-ratings")
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(adminService.getAllRatings());
    }
}





