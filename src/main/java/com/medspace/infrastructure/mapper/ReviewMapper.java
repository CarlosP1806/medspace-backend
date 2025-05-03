package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.Review;
import com.medspace.infrastructure.entity.ReviewEntity;
import com.medspace.infrastructure.entity.UserEntity;

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
        UserEntity userEntity = reviewEntity.getAuthor();
        review.setAuthor(UserMapper.toDomain(userEntity));
        review.setClinic(ClinicMapper.toDomain(reviewEntity.getClinic()));
        // review.setRentAgreement(RentAgreementMapper.toDomain(reviewEntity.getRentAgreement()));
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
        reviewEntity.setAuthor(UserMapper.toEntity(review.getAuthor()));
        reviewEntity.setClinic(ClinicMapper.toEntity(review.getClinic()));

        return reviewEntity;
    }
}
