package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.Clinic;
import com.medspace.infrastructure.entity.ClinicEntity;

public class ClinicMapper {

    public static Clinic toDomain(ClinicEntity clinicEntity) {
        if (clinicEntity == null) {
            return null;
        }

        Clinic clinic = new Clinic();

        clinic.setId(clinicEntity.getId());
        clinic.setDisplayName(clinicEntity.getDisplayName());
        clinic.setCategory(clinicEntity.getCategory());
        clinic.setDescription(clinicEntity.getDescription());

        clinic.setPricePerDay(clinicEntity.getPricePerDay());
        clinic.setMaxStayDays(clinicEntity.getMaxStayDays());
        clinic.setAvailableFromDate(clinicEntity.getAvailableFromDate());
        clinic.setAvailableToDate(clinicEntity.getAvailableToDate());

        clinic.setAddressStreet(clinicEntity.getAddressStreet());
        clinic.setAddressCity(clinicEntity.getAddressCity());
        clinic.setAddressState(clinicEntity.getAddressState());
        clinic.setAddressCountry(clinicEntity.getAddressCountry());
        clinic.setAddressZip(clinicEntity.getAddressZip());
        clinic.setAddressLongitude(clinicEntity.getAddressLongitude());
        clinic.setAddressLatitude(clinicEntity.getAddressLatitude());

        clinic.setSize(clinicEntity.getSize());

        clinic.setLandlord(UserMapper.toDomain(clinicEntity.getLandlord()));

        clinic.setCreatedAt(clinicEntity.getCreatedAt());

        return clinic;
    }

    public static ClinicEntity toEntity(Clinic clinic) {
        if (clinic == null) {
            return null;
        }

        ClinicEntity clinicEntity = new ClinicEntity();

        clinicEntity.setId(clinic.getId());
        clinicEntity.setDisplayName(clinic.getDisplayName());
        clinicEntity.setCategory(clinic.getCategory());
        clinicEntity.setDescription(clinic.getDescription());

        clinicEntity.setPricePerDay(clinic.getPricePerDay());
        clinicEntity.setMaxStayDays(clinic.getMaxStayDays());
        clinicEntity.setAvailableFromDate(clinic.getAvailableFromDate());
        clinicEntity.setAvailableToDate(clinic.getAvailableToDate());

        clinicEntity.setAddressStreet(clinic.getAddressStreet());
        clinicEntity.setAddressCity(clinic.getAddressCity());
        clinicEntity.setAddressState(clinic.getAddressState());
        clinicEntity.setAddressCountry(clinic.getAddressCountry());
        clinicEntity.setAddressZip(clinic.getAddressZip());
        clinicEntity.setAddressLongitude(clinic.getAddressLongitude());
        clinicEntity.setAddressLatitude(clinic.getAddressLatitude());

        clinicEntity.setSize(clinic.getSize());

        clinicEntity.setLandlord(UserMapper.toEntity(clinic.getLandlord()));

        clinicEntity.setCreatedAt(clinic.getCreatedAt());

        return clinicEntity;
    }
}
