package com.medspace.infrastructure.repository;

import com.medspace.domain.model.Review;
import com.medspace.domain.repository.ReviewRepository;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.entity.ReviewEntity;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.mapper.ReviewMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReviewRepositoryImpl
        implements ReviewRepository, PanacheRepositoryBase<ReviewEntity, Long> {

    @Inject
    UserRepositoryImpl userRepository;
    @Inject
    ClinicRepositoryImpl clinicRepository;

    // @Inject
    // RentAgreementRepositoryImpl rentAgreementRepository;

    @Override
    @Transactional
    public Review insertReview(Review review) {
        ReviewEntity reviewEntity = ReviewMapper.toEntity(review);
        persist(reviewEntity);
        return ReviewMapper.toDomain(reviewEntity);
    }


    @Override
    public List<Review> getAllReviews() {
        List<ReviewEntity> reviewEntities = listAll();
        List<Review> reviews = new ArrayList<>();
        for (ReviewEntity entity : reviewEntities) {
            reviews.add(ReviewMapper.toDomain(entity));
        }
        return reviews;
    }

    @Override
    public Review getReviewById(Long id) {
        ReviewEntity entity = findById(id);
        if (entity == null) {
            throw new NotFoundException("review with id " + id + " not found");
        }
        return ReviewMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public Void deleteReviewById(Long id) {
        ReviewEntity entity = findById(id);
        if (entity == null) {
            throw new NotFoundException("review with id " + id + " not found");
        }
        delete(entity);
        return null;
    }

    @Override
    @Transactional
    public Review assignReviewToRentAgreement(Long reviewId, Long rentAgreementId) {
        /*
         * ReviewEntity reviewEntity = findById(reviewId); if (reviewEntity == null) { throw new
         * NotFoundException("review with id " + reviewId + " not found"); }
         * 
         * RentAgreementEntity rentAgreementEntity =
         * rentAgreementRepository.findById(rentAgreementId); if (rentAgreementEntity == null) {
         * throw new NotFoundException("rent agreement with id " + rentAgreementId + " not found");
         * }
         * 
         * reviewEntity.setRentAgreement(rentAgreementEntity); persist(reviewEntity); return
         * ReviewMapper.toDomain(reviewEntity);
         */
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    @Transactional
    public Review assignReviewToAuthor(Long reviewId, Long authorId) {
        ReviewEntity reviewEntity = findById(reviewId);
        if (reviewEntity == null) {
            throw new NotFoundException("review with id " + reviewId + " not found");
        }

        UserEntity userEntity = userRepository.findById(authorId);
        if (userEntity == null) {
            throw new NotFoundException("author with id " + authorId + " not found");
        }

        reviewEntity.setAuthor(userEntity);
        persist(reviewEntity);
        return ReviewMapper.toDomain(reviewEntity);
    }

    @Override
    @Transactional
    public Review assignReviewToClinic(Long reviewId, Long clinicId) {
        ReviewEntity reviewEntity = findById(reviewId);
        if (reviewEntity == null) {
            throw new NotFoundException("review with id " + reviewId + " not found");
        }

        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("clinic with id " + clinicId + " not found");
        }

        reviewEntity.setClinic(clinicEntity);
        persist(reviewEntity);
        return ReviewMapper.toDomain(reviewEntity);
    }

    @Override
    public List<Review> getReviewsByClinicId(Long clinicId) {
        List<ReviewEntity> reviewEntities = list("clinic.id", clinicId);
        List<Review> reviews = reviewEntities.stream().map(ReviewMapper::toDomain).toList();
        return reviews;
    }
}
