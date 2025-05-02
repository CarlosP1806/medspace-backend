package com.medspace.application.service;

import com.medspace.domain.model.Review;
import com.medspace.domain.model.TenantFavoriteClinic;
import com.medspace.domain.repository.TenantFavoriteClinicRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class TenantFavoriteClinicService {
    @Inject
    TenantFavoriteClinicRepository tenantFavoriteClinicRepository;

    public TenantFavoriteClinic createTenantFavoriteClinic(TenantFavoriteClinic favoriteClinic) {
        return tenantFavoriteClinicRepository.createTenantFavoriteClinic(favoriteClinic);
    }

    public TenantFavoriteClinic getFavoriteClinicById(Long id) {
        return tenantFavoriteClinicRepository.getFavoriteClinicById(id);
    }

    public List<TenantFavoriteClinic> getFavoriteClinicsByTenantId(Long tenantId) {
        return tenantFavoriteClinicRepository.getFavoriteClinicsByTenantId(tenantId);
    }

    public List<TenantFavoriteClinic> getFavoriteClinicsByClinicId(Long clinicId) {
        return tenantFavoriteClinicRepository.getFavoriteClinicsByClinicId(clinicId);
    }

    public void removeFavoriteClinic(Long tenantId, Long clinicId) {
        tenantFavoriteClinicRepository.removeFavoriteClinic(tenantId, clinicId);
    }

    public boolean isFavoriteClinic(Long tenantId, Long clinicId) {
        return tenantFavoriteClinicRepository.isFavoriteClinic(tenantId, clinicId);
    }

    public TenantFavoriteClinic assignToTenant(Long favoriteClinicId, Long tenantId) {
        return tenantFavoriteClinicRepository.assignToTenant(favoriteClinicId, tenantId);
    }

    public TenantFavoriteClinic assignToClinic(Long favoriteClinicId, Long clinicId) {
        return tenantFavoriteClinicRepository.assignToClinic(favoriteClinicId, clinicId);
    }

    public Boolean validateFavoriteClinicOwnership(Long favoriteId, Long tenantId) {
        if (favoriteId == null || tenantId == null) {
            return false;
        }
        TenantFavoriteClinic favorite =
                tenantFavoriteClinicRepository.getFavoriteClinicById(favoriteId);
        if (favorite == null || favorite.getTenant() == null) {
            return false;
        }
        return favorite.getTenant().getId().equals(tenantId);
    }



}
