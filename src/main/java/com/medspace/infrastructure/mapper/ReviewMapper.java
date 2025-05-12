package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.Review;
import com.medspace.infrastructure.entity.ReviewEntity;

public class ReviewMapper {
    public static Review toDomain(ReviewEntity reviewEntity) {
        if (reviewEntity == null) {
            return null;
        }
        Review review = new Review();
        review.setId(reviewEntity.getId());
        review.setType(reviewEntity.getType());
        review.setRating(reviewEntity.getRating());
        review.setComment(reviewEntity.getComment());
        review.setCreatedAt(reviewEntity.getCreatedAt());
        review.setRentRequest(RentRequestMapper.toDomain(reviewEntity.getRentRequest()));
        return review;
    }

    public static ReviewEntity toEntity(Review review) {
        if (review == null) {
            return null;
        }

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(review.getId());
        reviewEntity.setType(review.getType());
        reviewEntity.setRating(review.getRating());
        reviewEntity.setComment(review.getComment());
        reviewEntity.setCreatedAt(review.getCreatedAt());
        reviewEntity.setRentRequest(RentRequestMapper.toEntity(review.getRentRequest()));

        return reviewEntity;
    }
}
