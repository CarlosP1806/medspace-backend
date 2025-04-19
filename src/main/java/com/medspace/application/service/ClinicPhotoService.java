package com.medspace.application.service;

import com.medspace.application.service.external.FileStorageService;
import com.medspace.domain.model.ClinicPhoto;
import com.medspace.domain.repository.ClinicPhotoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.InputStream;
import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class ClinicPhotoService {
    @Inject
    ClinicPhotoRepository clinicPhotoRepository;
    @Inject
    FileStorageService fileStorageService;

    public ClinicPhoto createPhoto(ClinicPhoto clinicPhoto, InputStream photoInputStream) {
        clinicPhoto.setCreatedAt(Instant.now());
        String savedUrl = fileStorageService.saveFile(photoInputStream);
        clinicPhoto.setUrl(savedUrl);

        clinicPhoto = clinicPhotoRepository.insertPhoto(clinicPhoto);

        return clinicPhoto;
    }

    public ClinicPhoto assignPhotoToClinic(Long clinicPhotoId, Long clinicId) {
        return clinicPhotoRepository.assignPhotoToClinic(clinicPhotoId, clinicId);
    }

    public List<ClinicPhoto> listPhotosByClinicId(Long clinicId) {
        return clinicPhotoRepository.getClinicPhotosByClinicId(clinicId);
    }

    public void deletePhoto(Long id) {
        clinicPhotoRepository.deletePhotoById(id);
    }

    public ClinicPhoto getPhotoById(Long id) {
        return clinicPhotoRepository.getPhotoById(id);
    }

    public void setPhotoAsPrimary(Long id) {
        clinicPhotoRepository.setPhotoAsPrimary(id);
    }
}
