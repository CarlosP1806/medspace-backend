package com.medspace.domain.repository;

import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.TenantFavoriteClinic;

import java.util.List;

public interface TenantFavoriteClinicRepository {
    TenantFavoriteClinic createTenantFavoriteClinic(TenantFavoriteClinic tenantFavoriteClinic);

    TenantFavoriteClinic getFavoriteClinicById(Long id);

    void deleteFavoriteClinicById(Long id);

    List<TenantFavoriteClinic> getFavoriteClinicsByTenantId(Long tenantId);

    List<TenantFavoriteClinic> getFavoriteClinicsByClinicId(Long clinicId);

    boolean isFavoriteClinic(Long tenantId, Long clinicId);

    void removeFavoriteClinic(Long tenantId, Long clinicId);

    TenantFavoriteClinic assignToTenant(Long favoriteClinicId, Long tenantId);

    TenantFavoriteClinic assignToClinic(Long favoriteClinicId, Long clinicId);

}
