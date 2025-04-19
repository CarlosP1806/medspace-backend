package com.medspace.infrastructure.repository;

import com.medspace.domain.model.ClinicAvailability;
import com.medspace.domain.repository.ClinicAvailabilityRepository;
import com.medspace.infrastructure.entity.ClinicAvailabilityEntity;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.mapper.ClinicAvailabilityMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClinicAvailabilityRepositoryImpl implements ClinicAvailabilityRepository,
        PanacheRepositoryBase<ClinicAvailabilityEntity, Long> {
    @Inject
    ClinicRepositoryImpl clinicRepository;

    @Transactional
    @Override
    public ClinicAvailability insertAvailability(ClinicAvailability clinicAvailability) {
        ClinicAvailabilityEntity clinicAvailabilityEntity = ClinicAvailabilityMapper.toEntity(clinicAvailability);
        persist(clinicAvailabilityEntity);
        clinicAvailability = ClinicAvailabilityMapper.toDomain(clinicAvailabilityEntity);
        return clinicAvailability;
    }

    @Override
    @Transactional
    public ClinicAvailability updateAvailability(Long id, ClinicAvailability clinicAvailability) {
        ClinicAvailabilityEntity clinicAvailabilityEntity = findById(id);
        if (clinicAvailabilityEntity == null) {
            throw new NotFoundException("ClinicAvailability with id " + id + " not found");
        }

        clinicAvailabilityEntity.setWeekDay(clinicAvailability.getWeekDay());
        clinicAvailabilityEntity.setStartTime(clinicAvailability.getStartTime());
        clinicAvailabilityEntity.setEndTime(clinicAvailability.getEndTime());

        persist(clinicAvailabilityEntity);
        return ClinicAvailabilityMapper.toDomain(clinicAvailabilityEntity);
    }

    @Override
    @Transactional
    public void deleteAvailabilityById(Long id) {
        ClinicAvailabilityEntity clinicAvailabilityEntity = findById(id);
        if (clinicAvailabilityEntity != null) {
            delete(clinicAvailabilityEntity);
        } else {
            throw new NotFoundException("ClinicAvailability with id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public ClinicAvailability assignAvailabilityToClinic(Long clinicAvailabilityId, Long clinicId) {
        ClinicAvailabilityEntity clinicAvailabilityEntity = findById(clinicAvailabilityId);
        if (clinicAvailabilityEntity == null) {
            throw new NotFoundException("ClinicAvailability with id " + clinicAvailabilityId + " not found");
        }

        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not found");
        }

        clinicAvailabilityEntity.setClinic(clinicEntity);
        persist(clinicAvailabilityEntity);
        return ClinicAvailabilityMapper.toDomain(clinicAvailabilityEntity);
    }

    public List<ClinicAvailability> getAvailabilitiesByClinicId(Long clinicId) {
        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not found");
        }

        List<ClinicAvailability> clinicAvailabilities = new ArrayList<>();
        for(ClinicAvailabilityEntity clinicAvailabilityEntity : clinicEntity.getAvailabilities()) {
            clinicAvailabilities.add(ClinicAvailabilityMapper.toDomain(clinicAvailabilityEntity));
        }

        return clinicAvailabilities;
    }
}
