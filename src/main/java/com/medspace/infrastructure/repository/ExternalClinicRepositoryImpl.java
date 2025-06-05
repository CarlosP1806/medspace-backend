package com.medspace.infrastructure.repository;

import com.medspace.domain.model.ExternalClinic;
import com.medspace.domain.repository.ExternalClinicRepository;
import com.medspace.infrastructure.dto.externalClinic.GetExternalClinicSpecialistsDashboardDTO;
import com.medspace.infrastructure.dto.common.PaginationDTO;
import com.medspace.infrastructure.mapper.ExternalClinicMapper;
import com.medspace.infrastructure.entity.ExternalClinicEntity;
import com.medspace.infrastructure.util.SpecialtyDetector;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ExternalClinicRepositoryImpl
        implements ExternalClinicRepository, PanacheRepositoryBase<ExternalClinicEntity, Long> {

    @Override
    @Transactional
    public void saveAll(List<ExternalClinic> clinics) {
        List<ExternalClinicEntity> entitiesToPersist = new ArrayList<>();
        for (ExternalClinic clinicDomain : clinics) {
            if (clinicDomain.getExternalId() == null
                    || clinicDomain.getExternalId().trim().isEmpty()) {
                // Potentially log a warning or skip this record if externalId is crucial and
                // missing
                // For now, let's assume we map it and let persistence fail or succeed based on
                // other constraints
                ExternalClinicEntity newEntity = ExternalClinicMapper.toEntity(clinicDomain);
                entitiesToPersist.add(newEntity);
                continue;
            }

            ExternalClinicEntity existingEntity =
                    find("externalId", clinicDomain.getExternalId()).firstResult();

            if (existingEntity != null) {
                // Update existing entity
                ExternalClinicMapper.updateEntityFromDomain(clinicDomain, existingEntity);
                entitiesToPersist.add(existingEntity); // Add the updated, managed entity
            } else {
                // Create new entity
                ExternalClinicEntity newEntity = ExternalClinicMapper.toEntity(clinicDomain);
                entitiesToPersist.add(newEntity);
            }
        }
        persist(entitiesToPersist);
    }

    @Override
    public List<GetExternalClinicSpecialistsDashboardDTO> getExternalClinicSpecialistsDashboardData(
            PaginationDTO pagination) {
        return find("SELECT e FROM ExternalClinicEntity e")
                .page(pagination.getPage(), pagination.getSize()).list().stream()
                .map(ExternalClinicMapper::toDashboardDTO).collect(Collectors.toList());
    }

    @Override
    public List<GetExternalClinicSpecialistsDashboardDTO> getAllExternalClinicSpecialistsDashboardData() {
        List<GetExternalClinicSpecialistsDashboardDTO> allData = new ArrayList<>();
        int page = 0;
        int pageSize = 1000;

        while (true) {
            List<GetExternalClinicSpecialistsDashboardDTO> pageData =
                    find("SELECT e FROM ExternalClinicEntity e").page(page, pageSize).list()
                            .stream().map(ExternalClinicMapper::toDashboardDTO)
                            .collect(Collectors.toList());

            if (pageData.isEmpty()) {
                break;
            }

            allData.addAll(pageData);
            page++;
        }

        return allData;
    }

    @Override
    @Transactional
    public void updateSpecialties() {
        List<ExternalClinicEntity> entities = listAll();
        for (ExternalClinicEntity entity : entities) {
            entity.setSpecialty(SpecialtyDetector.detectSpecialty(entity.getNombre()));
        }
        persist(entities);
    }
}
