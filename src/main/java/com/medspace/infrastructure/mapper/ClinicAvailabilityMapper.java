package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.ClinicAvailability;
import com.medspace.infrastructure.entity.ClinicAvailabilityEntity;

public class ClinicAvailabilityMapper {
    public static ClinicAvailability toDomain(ClinicAvailabilityEntity clinicAvailabilityEntity) {
        if (clinicAvailabilityEntity == null) {
            return null;
        }

        ClinicAvailability clinicAvailability = new ClinicAvailability();
        clinicAvailability.setId(clinicAvailabilityEntity.getId());
        clinicAvailability.setWeekDay(clinicAvailabilityEntity.getWeekDay());
        clinicAvailability.setStartTime(clinicAvailabilityEntity.getStartTime());
        clinicAvailability.setEndTime(clinicAvailabilityEntity.getEndTime());

        clinicAvailability.setClinic(ClinicMapper.toDomain(clinicAvailabilityEntity.getClinic()));

        clinicAvailability.setCreatedAt(clinicAvailabilityEntity.getCreatedAt());

        return clinicAvailability;
    }

    public static ClinicAvailabilityEntity toEntity(ClinicAvailability clinicAvailability) {
        if (clinicAvailability == null) {
            return null;
        }

        ClinicAvailabilityEntity clinicAvailabilityEntity = new ClinicAvailabilityEntity();
        clinicAvailabilityEntity.setId(clinicAvailability.getId());
        clinicAvailabilityEntity.setWeekDay(clinicAvailability.getWeekDay());
        clinicAvailabilityEntity.setStartTime(clinicAvailability.getStartTime());
        clinicAvailabilityEntity.setEndTime(clinicAvailability.getEndTime());

        clinicAvailabilityEntity.setClinic(ClinicMapper.toEntity(clinicAvailability.getClinic()));

        clinicAvailabilityEntity.setCreatedAt(clinicAvailability.getCreatedAt());

        return clinicAvailabilityEntity;
    }
}
