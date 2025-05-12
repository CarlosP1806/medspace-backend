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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ReviewEntity> query = cb.createQuery(ReviewEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<ReviewEntity> review = query.from(ReviewEntity.class);

        Join<ReviewEntity, RentRequestEntity> rentRequestJoin = review.join("rentRequest");
        Join<RentRequestEntity, ClinicEntity> clinicJoin = rentRequestJoin.join("clinic");
        Join<ClinicEntity, UserEntity> userJoin = clinicJoin.join("landlord");

        predicates.add(cb.equal(userJoin.get("id"), landlordId));
        predicates.add(cb.equal(review.get("type"), Review.Type.LANDLORD));

        query.select(review).distinct(true).where(cb.and(predicates.toArray(new Predicate[0])));
        List<ReviewEntity> entities = em.createQuery(query).getResultList();

        return entities.stream().map(ReviewMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Review> getReviewsByTenantId(Long tenantId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ReviewEntity> query = cb.createQuery(ReviewEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<ReviewEntity> review = query.from(ReviewEntity.class);

        Join<ReviewEntity, RentRequestEntity> rentRequestJoin = review.join("rentRequest");
        Join<RentRequestEntity, UserEntity> userJoin = rentRequestJoin.join("tenant");

        predicates.add(cb.equal(userJoin.get("id"), tenantId));
        predicates.add(cb.equal(review.get("type"), Review.Type.TENANT));

        query.select(review).distinct(true).where(cb.and(predicates.toArray(new Predicate[0])));
        List<ReviewEntity> entities = em.createQuery(query).getResultList();

        return entities.stream().map(ReviewMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Review> getReviewsByClinicId(Long clinicId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ReviewEntity> query = cb.createQuery(ReviewEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<ReviewEntity> review = query.from(ReviewEntity.class);

        Join<ReviewEntity, RentRequestEntity> rentRequestJoin = review.join("rentRequest");
        Join<RentRequestEntity, ClinicEntity> clinicJoin = rentRequestJoin.join("clinic");

        predicates.add(cb.equal(clinicJoin.get("id"), clinicId));
        predicates.add(cb.equal(review.get("type"), Review.Type.CLINIC));

        query.select(review).distinct(true).where(cb.and(predicates.toArray(new Predicate[0])));
        List<ReviewEntity> entities = em.createQuery(query).getResultList();

        return entities.stream().map(ReviewMapper::toDomain).collect(Collectors.toList());
    }
}
