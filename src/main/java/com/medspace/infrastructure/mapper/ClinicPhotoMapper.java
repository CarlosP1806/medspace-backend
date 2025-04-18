package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.ClinicPhoto;
import com.medspace.infrastructure.entity.ClinicPhotoEntity;

public class ClinicPhotoMapper {
    public static ClinicPhoto toDomain(ClinicPhotoEntity clinicPhotoEntity) {
        ClinicPhoto clinicPhoto = new ClinicPhoto();
        clinicPhoto.setId(clinicPhotoEntity.getId());
        clinicPhoto.setUrl(clinicPhotoEntity.getUrl());

        clinicPhoto.setClinic(ClinicMapper.toDomain(clinicPhotoEntity.getClinic()));

        clinicPhoto.setCreatedAt(clinicPhotoEntity.getCreatedAt());

        return clinicPhoto;
    }

    public static ClinicPhotoEntity toEntity(ClinicPhoto clinicPhoto) {
        ClinicPhotoEntity clinicPhotoEntity = new ClinicPhotoEntity();
        clinicPhotoEntity.setId(clinicPhoto.getId());
        clinicPhotoEntity.setUrl(clinicPhoto.getUrl());

        clinicPhotoEntity.setClinic(ClinicMapper.toEntity(clinicPhoto.getClinic()));

        clinicPhotoEntity.setCreatedAt(clinicPhoto.getCreatedAt());

        return clinicPhotoEntity;
    }
}
