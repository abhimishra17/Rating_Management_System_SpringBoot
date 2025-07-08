package com.springBootproject.RatingManagement.repository;

import com.springBootproject.RatingManagement.entity.Rating;
import com.springBootproject.RatingManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    //  Get all ratings submitted by a specific user
    List<Rating> findByUser(User user);
}

