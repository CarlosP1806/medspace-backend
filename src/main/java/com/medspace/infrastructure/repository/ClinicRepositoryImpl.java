package com.medspace.infrastructure.repository;

import com.medspace.domain.model.Clinic;
import com.medspace.domain.repository.ClinicRepository;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.mapper.ClinicMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClinicRepositoryImpl
        implements ClinicRepository, PanacheRepositoryBase<ClinicEntity, Long> {
    @Inject
    UserRepositoryImpl userRepository;

    @Override
    @Transactional
    public Clinic insertClinic(Clinic clinic) {
        ClinicEntity clinicEntity = ClinicMapper.toEntity(clinic);
        persist(clinicEntity);
        clinic = ClinicMapper.toDomain(clinicEntity);
        return clinic;
    }

    @Override
    public List<Clinic> getAllClinics() {
        List<ClinicEntity> clinicEntities = listAll();
        List<Clinic> clinics = new ArrayList<>();

        for (ClinicEntity clinicEntity : clinicEntities) {
            clinics.add(ClinicMapper.toDomain(clinicEntity));
        }

        return clinics;
    }

    @Override
    public List<Clinic> getClinicsByLandlordId(Long landlordId) {
        List<ClinicEntity> clinicEntities = find("landlord.id", landlordId).list();
        List<Clinic> clinics = new ArrayList<>();

        for (ClinicEntity clinicEntity : clinicEntities) {
            clinics.add(ClinicMapper.toDomain(clinicEntity));
        }

        return clinics;
    }

    @Override
    public Clinic getClinicById(Long id) {
        ClinicEntity clinicEntity = findById(id);
        if (clinicEntity == null) {
            return null;
        }
        return ClinicMapper.toDomain(clinicEntity);
    }

    @Override
    @Transactional
    public void deleteClinicById(Long id) {
        ClinicEntity clinicEntity = findById(id);
        if (clinicEntity != null) {
            delete(clinicEntity);
        } else {
            throw new NotFoundException("clinic with id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public Clinic assignClinicToUser(Long clinicId, Long userId) {
        ClinicEntity clinicEntity = findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("clinic with id " + clinicId + " not found");
        }

        UserEntity userEntity = userRepository.findById(userId);
        if (userEntity == null) {
            throw new NotFoundException("user with id " + userId + " not found");
        }

        clinicEntity.setLandlord(userEntity);
        persist(clinicEntity);
        return ClinicMapper.toDomain(clinicEntity);
    }
}
