package com.routeplanner.smartrouteplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.routeplanner.smartrouteplanner.model.Review;
import com.routeplanner.smartrouteplanner.repository.ReviewRepository;
import com.routeplanner.smartrouteplanner.service.EmailService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private EmailService emailService;

    @PostMapping("/add")
    public Review addReview(@RequestBody Review review){

        // Save review to database
        Review savedReview = reviewRepo.save(review);

        // Send confirmation email to the user
        emailService.sendConfirmationEmail(
            review.getUserEmail(),
            "Review Received - Smart Route Planner",
            "Thank you for submitting your review for Smart Route Planner!\n\nWe appreciate your feedback and hope you continue using our service."
        );

        return savedReview;
    }

    @GetMapping("/list")
    public List<Review> getReviews(@RequestParam String email){
        return reviewRepo.findByUserEmail(email);
    }
}