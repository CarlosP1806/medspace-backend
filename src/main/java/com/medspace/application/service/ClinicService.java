package com.medspace.application.service;

import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.Review;
import com.medspace.domain.repository.ClinicRepository;
import com.medspace.domain.repository.ReviewRepository;
import com.medspace.infrastructure.dto.clinic.ClinicQueryFilterDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class ClinicService {
    @Inject
    ClinicRepository clinicRepository;

    @Inject
    ReviewRepository reviewRepository;

    public Clinic createClinic(Clinic clinic) {
        clinic.setCreatedAt(Instant.now());
        clinic = clinicRepository.insertClinic(clinic);
        return clinic;
    }

    public Clinic getClinicById(Long id) {
        return clinicRepository.getClinicById(id);
    }

    public void deleteClinicById(Long id) {
        clinicRepository.deleteClinicById(id);
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.getAllClinics();
    }

    public List<Clinic> getFilteredClinics(ClinicQueryFilterDTO queryFilterDTO) {
        return clinicRepository.getFilteredClinics(queryFilterDTO);
    }

    public List<Clinic> getClinicsByLandlordId(Long landlordId) {
        return clinicRepository.getClinicsByLandlordId(landlordId);
    }

    public Clinic assignLandlord(Long clinicId, Long userId) {
        return clinicRepository.assignClinicToUser(clinicId, userId);
    }

    public Boolean validateClinicOwnership(Long clinicId, Long userId) {
        if (clinicId == null || userId == null) {
            return false;
        }

        Clinic clinic = clinicRepository.getClinicById(clinicId);
        if (clinic == null) {
            return false;
        }
        return clinic.getLandlord().getId().equals(userId);
    }

    public Double getAverageRatingById(Long clinicId) {
        List<Review> reviews = reviewRepository.getReviewsByClinicId(clinicId);

        if (reviews.isEmpty()) {
            return null;
        }
        int totalRating = reviews.stream().mapToInt(Review::getRating).sum();
        return Double.valueOf(totalRating) / reviews.size();
    }
}
