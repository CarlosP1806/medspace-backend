package com.medspace.domain.repository;

import com.medspace.domain.model.ClinicPhoto;

import java.util.List;

public interface ClinicPhotoRepository {
    public ClinicPhoto insertPhoto(ClinicPhoto clinicPhoto);

    public ClinicPhoto getPhotoById(Long id);

    public void deletePhotoById(Long id);

    public ClinicPhoto assignPhotoToClinic(Long clinicPhotoId, Long clinicId);

    public List<ClinicPhoto> getClinicPhotosByClinicId(Long clinicId);

    public void setPhotoAsPrimary(Long id);
}
