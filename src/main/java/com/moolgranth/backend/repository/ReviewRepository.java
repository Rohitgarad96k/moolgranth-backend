package com.moolgranth.backend.repository;

import com.moolgranth.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Finds all reviews for a specific product, newest first
    List<Review> findByProductIdOrderByCreatedAtDesc(Long productId);
}