package com.medspace.infrastructure.repository;

import com.medspace.domain.model.ClinicEquipment;
import com.medspace.domain.repository.ClinicEquipmentRepository;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.entity.ClinicEquipmentEntity;
import com.medspace.infrastructure.mapper.ClinicEquipmentMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClinicEquipmentRepositoryImpl implements ClinicEquipmentRepository, PanacheRepositoryBase<ClinicEquipmentEntity, Long> {
    @Inject
    ClinicRepositoryImpl clinicRepository;

    @Transactional
    @Override
    public ClinicEquipment insertEquipment(ClinicEquipment clinicEquipment) {
        ClinicEquipmentEntity clinicEquipmentEntity = ClinicEquipmentMapper.toEntity(clinicEquipment);
        persist(clinicEquipmentEntity);
        clinicEquipment = ClinicEquipmentMapper.toDomain(clinicEquipmentEntity);
        return clinicEquipment;
    }

    @Override
    public ClinicEquipment getEquipmentById(Long id) {
        ClinicEquipmentEntity clinicEquipmentEntity = findById(id);
        if(clinicEquipmentEntity == null) {
            throw new NotFoundException("ClinicEquipment with id " + id + " not found");
        }
        return ClinicEquipmentMapper.toDomain(clinicEquipmentEntity);
    }

    @Override
    @Transactional
    public void deleteEquipmentById(Long id) {
        ClinicEquipmentEntity clinicEquipmentEntity = findById(id);
        if(clinicEquipmentEntity != null) {
           delete(clinicEquipmentEntity);
        } else {
            throw new NotFoundException("ClinicEquipment with id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public ClinicEquipment assignEquipmentToClinic(Long clinicEquipmentId, Long clinicId) {
        ClinicEquipmentEntity clinicEquipmentEntity = findById(clinicEquipmentId);
        if(clinicEquipmentEntity == null) {
            throw new NotFoundException("ClinicEquipment with id " + clinicEquipmentId + " not found");
        }

        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if(clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not found");
        }

        clinicEquipmentEntity.setClinic(clinicEntity);
        persist(clinicEquipmentEntity);
        return ClinicEquipmentMapper.toDomain(clinicEquipmentEntity);
    }

    @Override
    public List<ClinicEquipment> getEquipmentsByClinicId(Long clinicId) {
        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if(clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not found");
        }

        List<ClinicEquipment> clinicEquipments = new ArrayList<>();
        for(ClinicEquipmentEntity clinicEquipmentEntity : clinicEntity.getEquipments()) {
            clinicEquipments.add(ClinicEquipmentMapper.toDomain(clinicEquipmentEntity));
        }

        return clinicEquipments;
    }
}
