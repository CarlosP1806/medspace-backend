package com.medspace.infrastructure.repository;

import com.medspace.domain.model.ClinicPhoto;
import com.medspace.domain.repository.ClinicPhotoRepository;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.entity.ClinicPhotoEntity;
import com.medspace.infrastructure.mapper.ClinicPhotoMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClinicPhotoRepositoryImpl implements ClinicPhotoRepository, PanacheRepositoryBase<ClinicPhotoEntity, Long> {
    @Inject
    ClinicRepositoryImpl clinicRepository;

    @Transactional
    @Override
    public ClinicPhoto insertPhoto(ClinicPhoto clinicPhoto) {
        ClinicPhotoEntity clinicPhotoEntity = ClinicPhotoMapper.toEntity(clinicPhoto);
        persist(clinicPhotoEntity);
        clinicPhoto = ClinicPhotoMapper.toDomain(clinicPhotoEntity);
        return clinicPhoto;
    }

    @Override
    public ClinicPhoto getPhotoById(Long id) {
        ClinicPhotoEntity clinicPhotoEntity = findById(id);
        if (clinicPhotoEntity == null) {
            throw new NotFoundException("ClinicPhoto with id " + id + " not Found");
        }
        return ClinicPhotoMapper.toDomain(clinicPhotoEntity);
    }

    @Override
    @Transactional
    public void deletePhotoById(Long id) {
        ClinicPhotoEntity clinicPhotoEntity = findById(id);
        if (clinicPhotoEntity != null) {
            delete(clinicPhotoEntity);
        } else {
            throw new NotFoundException("ClinicPhoto with id " + id + " not Found");
        }
    }

    @Override
    @Transactional
    public ClinicPhoto assignPhotoToClinic(Long clinicPhotoId, Long clinicId) {
        ClinicPhotoEntity clinicPhotoEntity = findById(clinicPhotoId);
        if (clinicPhotoEntity == null) {
            throw new NotFoundException("ClinicPhoto with id " + clinicPhotoId + " not Found");
        }

        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not Found");
        }

        clinicPhotoEntity.setClinic(clinicEntity);
        persist(clinicPhotoEntity);
        return ClinicPhotoMapper.toDomain(clinicPhotoEntity);
    }

    public List<ClinicPhoto> getClinicPhotosByClinicId(Long clinicId) {
        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not Found");
        }

        List<ClinicPhoto> clinicPhotos = new ArrayList<>();
        for(ClinicPhotoEntity clinicPhotoEntity : clinicEntity.getPhotos()) {
            clinicPhotos.add(ClinicPhotoMapper.toDomain(clinicPhotoEntity));
        }

        return clinicPhotos;
    }
}
