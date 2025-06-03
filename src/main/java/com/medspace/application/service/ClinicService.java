package com.medspace.application.service;

import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.ClinicAvailability;
import com.medspace.domain.model.ClinicEquipment;
import com.medspace.domain.model.ClinicPhoto;
import com.medspace.domain.model.Review;
import com.medspace.domain.repository.ClinicAvailabilityRepository;
import com.medspace.domain.repository.ClinicEquipmentRepository;
import com.medspace.domain.repository.ClinicPhotoRepository;
import com.medspace.domain.repository.ClinicRepository;
import com.medspace.domain.repository.ReviewRepository;
import com.medspace.infrastructure.dto.clinic.ClinicQueryDTO;
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
    @Inject
    ClinicPhotoRepository clinicPhotoRepository;
    @Inject
    ClinicEquipmentRepository clinicEquipmentRepository;
    @Inject
    ClinicAvailabilityRepository clinicAvailabilityRepository;

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

    public List<Clinic> getFilteredClinics(ClinicQueryDTO queryFilterDTO) {
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

    // Photo methods

    public ClinicPhoto createClinicPhoto(ClinicPhoto clinicPhoto) {
        clinicPhoto.setCreatedAt(Instant.now());
        clinicPhoto = clinicPhotoRepository.insertPhoto(clinicPhoto);
        return clinicPhoto;
    }

    public ClinicPhoto assignPhotoToClinic(Long clinicPhotoId, Long clinicId) {
        return clinicPhotoRepository.assignPhotoToClinic(clinicPhotoId, clinicId);
    }

    public List<ClinicPhoto> listPhotosByClinicId(Long clinicId) {
        return clinicPhotoRepository.getClinicPhotosByClinicId(clinicId);
    }

    public String getMainPhotoPathByClinicId(Long clinicId) {
        List<ClinicPhoto> clinicPhotos = clinicPhotoRepository.getClinicPhotosByClinicId(clinicId);
        if (clinicPhotos.isEmpty()) {
            return null;
        }
        for (ClinicPhoto clinicPhoto : clinicPhotos) {
            if (clinicPhoto.getIsPrimary()) {
                return clinicPhoto.getPath();
            }
        }
        return null;
    }

    public void deleteClinicPhotoById(Long id) {
        clinicPhotoRepository.deletePhotoById(id);
    }

    public ClinicPhoto getClinicPhotoById(Long id) {
        return clinicPhotoRepository.getPhotoById(id);
    }

    public void setClinicPhotoAsPrimary(Long id) {
        clinicPhotoRepository.setPhotoAsPrimary(id);
    }

    public Boolean validateClinicPhotoOwnership(Long photoId, Long clinicId) {
        if (photoId == null || clinicId == null) {
            return false;
        }

        ClinicPhoto clinicPhoto = clinicPhotoRepository.getPhotoById(photoId);
        if (clinicPhoto == null) {
            return false;
        }
        return clinicPhoto.getClinic().getId().equals(clinicId);
    }

    // Equipment methods

    public ClinicEquipment createClinicEquipment(ClinicEquipment clinicEquipment) {
        clinicEquipment.setCreatedAt(Instant.now());
        clinicEquipment = clinicEquipmentRepository.insertEquipment(clinicEquipment);
        return clinicEquipment;
    }

    public ClinicEquipment assignEquipmentToClinic(Long clinicEquipmentId, Long clinicId) {
        return clinicEquipmentRepository.assignEquipmentToClinic(clinicEquipmentId, clinicId);
    }

    public List<ClinicEquipment> getEquipmentsByClinicId(Long clinicId) {
        return clinicEquipmentRepository.getEquipmentsByClinicId(clinicId);
    }

    public void deleteClinicEquipmentById(Long id) {
        clinicEquipmentRepository.deleteEquipmentById(id);
    }

    public ClinicEquipment getClinicEquipmentById(Long id) {
        return clinicEquipmentRepository.getEquipmentById(id);
    }

    // Availability methods

    public ClinicAvailability createClinicAvailability(ClinicAvailability clinicAvailability) {
        clinicAvailability.setCreatedAt(Instant.now());
        clinicAvailability = clinicAvailabilityRepository.insertAvailability(clinicAvailability);
        return clinicAvailability;
    }

    public ClinicAvailability assignAvailabilityToClinic(Long clinicAvailabilityId, Long clinicId) {
        return clinicAvailabilityRepository.assignAvailabilityToClinic(clinicAvailabilityId,
                clinicId);
    }

    public List<ClinicAvailability> getAvailabilitiesByClinicId(Long clinicId) {
        return clinicAvailabilityRepository.getAvailabilitiesByClinicId(clinicId);
    }

    public void deleteClinicAvailabilityById(Long id) {
        clinicAvailabilityRepository.deleteAvailabilityById(id);
    }

    public ClinicAvailability updateClinicAvailabilityById(Long id,
            ClinicAvailability clinicAvailability) {
        return clinicAvailabilityRepository.updateAvailability(id, clinicAvailability);
    }

    public ClinicAvailability getClinicAvailabilityById(Long id) {
        return clinicAvailabilityRepository.getAvailabilityById(id);
    }
    public long countAllClinics() {
        return clinicRepository.countAll();
    }
    public long countClinicsByCategory(Clinic.Category category) {
        return clinicRepository.countClinicsByCategory(category);
    }
    
}
