package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.Clinic;
import com.medspace.infrastructure.entity.ClinicEntity;

public class ClinicMapper {

    public static Clinic toDomain(ClinicEntity clinicEntity) {
        Clinic clinic = new Clinic();

        clinic.setId(clinicEntity.getId());
        clinic.setDisplayName(clinicEntity.getDisplayName());
        clinic.setCategory(clinicEntity.getCategory());

        clinic.setPricePerDay(clinicEntity.getPricePerDay());
        clinic.setMaxStayDays(clinicEntity.getMaxStayDays());

        clinic.setAddressStreet(clinicEntity.getAddressStreet());
        clinic.setAddressCity(clinicEntity.getAddressCity());
        clinic.setAddressState(clinicEntity.getAddressState());
        clinic.setAddressCountry(clinicEntity.getAddressCountry());
        clinic.setAddressZip(clinicEntity.getAddressZip());
        clinic.setAddressLongitude(clinicEntity.getAddressLongitude());
        clinic.setAddressLatitude(clinicEntity.getAddressLatitude());

        clinic.setCreatedAt(clinicEntity.getCreatedAt());

        return clinic;
    }

    public static ClinicEntity toEntity(Clinic clinic) {
        ClinicEntity clinicEntity = new ClinicEntity();

        clinicEntity.setId(clinic.getId());
        clinicEntity.setDisplayName(clinic.getDisplayName());
        clinicEntity.setCategory(clinic.getCategory());

        clinicEntity.setPricePerDay(clinic.getPricePerDay());
        clinicEntity.setMaxStayDays(clinic.getMaxStayDays());

        clinicEntity.setAddressStreet(clinic.getAddressStreet());
        clinicEntity.setAddressCity(clinic.getAddressCity());
        clinicEntity.setAddressState(clinic.getAddressState());
        clinicEntity.setAddressCountry(clinic.getAddressCountry());
        clinicEntity.setAddressZip(clinic.getAddressZip());
        clinicEntity.setAddressLongitude(clinic.getAddressLongitude());
        clinicEntity.setAddressLatitude(clinic.getAddressLatitude());

        clinicEntity.setCreatedAt(clinic.getCreatedAt());

        return clinicEntity;
    }
}
