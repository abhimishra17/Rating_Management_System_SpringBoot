package com.springBootproject.RatingManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each rating is linked to a user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Rating fields: 1 to 5
    @Column(nullable = false)
    private int ambiance;

    @Column(nullable = false)
    private int food;

    @Column(nullable = false)
    private int service;

    @Column(nullable = false)
    private int cleanliness;

    @Column(nullable = false)
    private int drinks;
}


