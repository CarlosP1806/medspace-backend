package com.medspace.infrastructure.repository;

import com.medspace.domain.model.Review;
import com.medspace.domain.repository.ReviewRepository;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.entity.RentRequestEntity;
import com.medspace.infrastructure.entity.ReviewEntity;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.mapper.ReviewMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReviewRepositoryImpl
        implements ReviewRepository, PanacheRepositoryBase<ReviewEntity, Long> {

    @Inject
    UserRepositoryImpl userRepository;
    @Inject
    RentRequestRepositoryImpl rentRequestRepository;
    @Inject
    ClinicRepositoryImpl clinicRepository;

    @PersistenceContext
    EntityManager em;

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
    public Review assignReviewToRentRequest(Long reviewId, Long rentRequestId) {
        ReviewEntity reviewEntity = findById(reviewId);
        if (reviewEntity == null) {
            throw new NotFoundException("review with id " + reviewId + " not found");
        }

        RentRequestEntity rentRequestEntity = rentRequestRepository.findById(rentRequestId);
        if (rentRequestEntity == null) {
            throw new NotFoundException("rent request with id " + rentRequestId + " not found");
        }

        reviewEntity.setRentRequest(rentRequestEntity);
        persist(reviewEntity);
        return ReviewMapper.toDomain(reviewEntity);
    }

    @Override
    public List<Review> getReviewsByLandlordId(Long landlordId) {
        UserEntity landlord = userRepository.findById(landlordId);
        List<ClinicEntity> clinics = landlord.getClinics();
        List<ReviewEntity> reviews = new ArrayList<>();
        for (ClinicEntity clinic : clinics) {
            Set<RentRequestEntity> rentRequests = clinic.getRentRequests();
            for (RentRequestEntity rentRequest : rentRequests) {
                List<ReviewEntity> reviewEntities = rentRequest.getReviews().stream()
                        .filter(reviewEntity -> reviewEntity.getType() == Review.Type.LANDLORD)
                        .collect(Collectors.toList());
                reviews.addAll(reviewEntities);
            }
        }
        return reviews.stream().map(ReviewMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Review> getReviewsByTenantId(Long tenantId) {
        UserEntity tenant = userRepository.findById(tenantId);
        List<RentRequestEntity> rentRequestOfTenant = tenant.getRentRequests();
        List<ReviewEntity> reviews = new ArrayList<>();

        for (RentRequestEntity rentRequest : rentRequestOfTenant) {
            List<ReviewEntity> reviewEntities = rentRequest.getReviews().stream()
                    .filter(reviewEntity -> reviewEntity.getType() == Review.Type.TENANT)
                    .collect(Collectors.toList());
            reviews.addAll(reviewEntities);
        }

        return reviews.stream().map(ReviewMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Review> getReviewsByClinicId(Long clinicId) {
        ClinicEntity clinic = clinicRepository.findById(clinicId);
        if (clinic == null) {
            return new ArrayList<>();
        }

        Set<RentRequestEntity> rentRequests = clinic.getRentRequests();
        List<ReviewEntity> reviews = new ArrayList<>();
        for (RentRequestEntity rentRequest : rentRequests) {
            List<ReviewEntity> reviewEntities = rentRequest.getReviews().stream()
                    .filter(reviewEntity -> reviewEntity.getType() == Review.Type.CLINIC)
                    .collect(Collectors.toList());
            reviews.addAll(reviewEntities);
        }

        return reviews.stream().map(ReviewMapper::toDomain).collect(Collectors.toList());
    }
}
