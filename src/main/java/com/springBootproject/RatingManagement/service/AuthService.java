package com.springBootproject.RatingManagement.service;

import com.springBootproject.RatingManagement.dto.AuthRequest;
import com.springBootproject.RatingManagement.dto.AuthResponse;
import com.springBootproject.RatingManagement.dto.RegisterRequest;
import com.springBootproject.RatingManagement.entity.User;
import com.springBootproject.RatingManagement.repository.UserRepository;
import com.springBootproject.RatingManagement.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {



    private final UserRepository userRepository;    //for database access for user
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;// Spring Security object to verify credentials

public String register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already exists");
    }

    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
   // user.setRole("USER");
    user.setRole(
            request.getRole() != null ? request.getRole().toUpperCase() : "USER"
    );

    userRepository.save(user);

    return "Registration successful. Please login to continue.";
}



    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = buildUserDetails(user);
        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, user.getRole());

    }

    private UserDetails buildUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
