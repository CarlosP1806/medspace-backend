package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.ClinicEquipment;
import com.medspace.infrastructure.entity.ClinicEquipmentEntity;

public class ClinicEquipmentMapper {
    public static ClinicEquipment toDomain(ClinicEquipmentEntity clinicEquipmentEntity) {
        if (clinicEquipmentEntity == null) {
            return null;
        }

        ClinicEquipment clinicEquipment = new ClinicEquipment();
        clinicEquipment.setId(clinicEquipmentEntity.getId());
        clinicEquipment.setType(clinicEquipmentEntity.getType());
        clinicEquipment.setQuantity(clinicEquipmentEntity.getQuantity());

        clinicEquipment.setClinic(ClinicMapper.toDomain(clinicEquipmentEntity.getClinic()));

        clinicEquipment.setCreatedAt(clinicEquipmentEntity.getCreatedAt());

        return clinicEquipment;
    }

    public static ClinicEquipmentEntity toEntity(ClinicEquipment clinicEquipment) {
        if (clinicEquipment == null) {
            return null;
        }

        ClinicEquipmentEntity clinicEquipmentEntity = new ClinicEquipmentEntity();
        clinicEquipmentEntity.setId(clinicEquipment.getId());
        clinicEquipmentEntity.setType(clinicEquipment.getType());
        clinicEquipmentEntity.setQuantity(clinicEquipment.getQuantity());

        clinicEquipmentEntity.setClinic(ClinicMapper.toEntity(clinicEquipment.getClinic()));

        clinicEquipmentEntity.setCreatedAt(clinicEquipment.getCreatedAt());

        return clinicEquipmentEntity;
    }
}
