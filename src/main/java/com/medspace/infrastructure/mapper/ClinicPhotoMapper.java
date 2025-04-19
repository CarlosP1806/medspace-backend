package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.ClinicPhoto;
import com.medspace.infrastructure.entity.ClinicPhotoEntity;

public class ClinicPhotoMapper {
    public static ClinicPhoto toDomain(ClinicPhotoEntity clinicPhotoEntity) {
        if (clinicPhotoEntity == null) {
            return null;
        }

        ClinicPhoto clinicPhoto = new ClinicPhoto();
        clinicPhoto.setId(clinicPhotoEntity.getId());
        clinicPhoto.setUrl(clinicPhotoEntity.getUrl());
        clinicPhoto.setIsPrimary(clinicPhotoEntity.getIsPrimary());

        clinicPhoto.setClinic(ClinicMapper.toDomain(clinicPhotoEntity.getClinic()));

        clinicPhoto.setCreatedAt(clinicPhotoEntity.getCreatedAt());

        return clinicPhoto;
    }

    public static ClinicPhotoEntity toEntity(ClinicPhoto clinicPhoto) {
        if (clinicPhoto == null) {
            return null;
        }

        ClinicPhotoEntity clinicPhotoEntity = new ClinicPhotoEntity();
        clinicPhotoEntity.setId(clinicPhoto.getId());
        clinicPhotoEntity.setUrl(clinicPhoto.getUrl());
        clinicPhotoEntity.setIsPrimary(clinicPhoto.getIsPrimary());

        clinicPhotoEntity.setClinic(ClinicMapper.toEntity(clinicPhoto.getClinic()));

        clinicPhotoEntity.setCreatedAt(clinicPhoto.getCreatedAt());

        return clinicPhotoEntity;
    }
}
