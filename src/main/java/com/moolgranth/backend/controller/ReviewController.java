package com.moolgranth.backend.controller;

import com.moolgranth.backend.entity.Review;
import com.moolgranth.backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = {"https://moolgranth.com", "https://www.moolgranth.com", "http://localhost:5173"}, allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // 🌟 NEW: Get ALL reviews (Perfect for your homepage testimonials!)
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewRepository.findAll());
    }

    // Get reviews for a specific product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewRepository.findByProductIdOrderByCreatedAtDesc(productId));
    }

    // Submit a new review
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review savedReview = reviewRepository.save(review);
        return ResponseEntity.ok(savedReview);
    }
}