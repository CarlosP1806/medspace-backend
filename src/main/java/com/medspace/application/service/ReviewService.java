package com.medspace.application.service;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.Review;
import com.medspace.domain.repository.ReviewRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class ReviewService {

    @Inject
    ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        review.setCreatedAt(Instant.now());
        review = reviewRepository.insertReview(review);
        return review;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.getReviewById(id);
    }

    public void deleteReviewById(Long id) {
        reviewRepository.deleteReviewById(id);
    }

    public Review assignAuthorToReview(Long reviewId, Long authorId) {
        return reviewRepository.assignReviewToAuthor(reviewId, authorId);
    }    

    public Review assignReviewToRentAgreement(Long reviewId, Long rentAgreementId) {
        return reviewRepository.assignReviewToRentAgreement(reviewId, rentAgreementId);
    }
    public Boolean validateReviewOwnership(Long reviewId, Long authorId) {
        if (reviewId == null || authorId == null) {
            return false;
        }
        Review review = reviewRepository.getReviewById(reviewId);
        if (review == null || review.getAuthor() == null) {
            return false;
        }
        // compara con authorId, no con reviewId
        return review.getAuthor().getId().equals(authorId);
    }
    
}
