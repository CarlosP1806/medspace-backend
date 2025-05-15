package com.medspace.infrastructure.repository;

import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.infrastructure.entity.RentRequestEntity;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.dto.rentRequest.RentRequestQueryFilterDTO;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.entity.TenantSpecialtyEntity;
import com.medspace.infrastructure.mapper.RentRequestMapper;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestSpecialistsDashboardDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentRequestRepositoryImpl
        implements RentRequestRepository, PanacheRepository<RentRequestEntity> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public RentRequest insert(RentRequest rentRequest, Long tenantId, Long clinicId) {
        RentRequestEntity entity = RentRequestMapper.toEntity(rentRequest);

        if (tenantId != null) {
            UserEntity tenant = entityManager.find(UserEntity.class, tenantId);
            if (tenant == null) {
                throw new NotFoundException("User with id " + rentRequest + " not found");
            }
            entity.setTenant(tenant);
        }

        if (clinicId != null) {
            ClinicEntity clinic = entityManager.find(ClinicEntity.class, clinicId);
            if (clinic == null) {
                throw new NotFoundException("Clinic with id " + clinicId + " not found");
            }
            entity.setClinic(clinic);
        }

        persist(entity);
        return RentRequestMapper.toDomain(entity);
    }

    @Override
    public List<RentRequest> findAllRequests() {
        return listAll().stream().map(RentRequestMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<RentRequest> findByLandlordId(RentRequestQueryFilterDTO filterDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentRequestEntity> cq = cb.createQuery(RentRequestEntity.class);
        Root<RentRequestEntity> root = cq.from(RentRequestEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        Join<RentRequestEntity, ClinicEntity> clinicJoin = root.join("clinic", JoinType.INNER);
        predicates.add(cb.equal(clinicJoin.get("landlord").get("id"), filterDTO.getUserId()));

        if (filterDTO.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), filterDTO.getStatus()));
        }

        cq.select(root).distinct(true).where(cb.and(predicates.toArray(new Predicate[0])));
        List<RentRequestEntity> entities = entityManager.createQuery(cq).getResultList();
        return entities.stream().map(RentRequestMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<RentRequest> findByTenantId(RentRequestQueryFilterDTO filterDTO) {
        List<RentRequestEntity> entities = find("tenant.id", filterDTO.getUserId()).list();

        if (filterDTO.getStatus() != null) {
            entities = entities.stream()
                    .filter(entity -> entity.getStatus().toString().equals(filterDTO.getStatus()))
                    .collect(Collectors.toList());
        }

        return entities.stream().map(RentRequestMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public RentRequest findRequestById(Long id) {
        RentRequestEntity entity = findById(id);
        if (entity == null) {
            throw new NotFoundException("RentRequest with id " + id + " not found");
        }
        return RentRequestMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        long deletedCount = delete("id", id);
        if (deletedCount == 0) {
            throw new NotFoundException("Cannot delete: RentRequest with id " + id + " not found");
        }
        return true;
    }

    @Override
    @Transactional
    public void updateRentRequestStatus(Long id, RentRequest.Status status) {
        RentRequestEntity entity = findById(id);
        if (entity == null) {
            throw new NotFoundException("RentRequest with id " + id + " not found");
        }
        entity.setStatus(status);
        persist(entity);
    }

    @Override
    public List<GetRentRequestSpecialistsDashboardDTO> getSpecialistsDashboardData() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentRequestEntity> cq = cb.createQuery(RentRequestEntity.class);
        Root<RentRequestEntity> root = cq.from(RentRequestEntity.class);

        Join<RentRequestEntity, UserEntity> tenantJoin = root.join("tenant", JoinType.INNER);
        Join<RentRequestEntity, ClinicEntity> clinicJoin = root.join("clinic", JoinType.INNER);
        Join<UserEntity, TenantSpecialtyEntity> specialtyJoin = tenantJoin.join("tenantSpecialty", JoinType.LEFT);

        // Add predicate to filter only accepted requests
        Predicate acceptedStatus = cb.equal(root.get("status"), RentRequest.Status.ACCEPTED);
        cq.select(root).distinct(true).where(acceptedStatus);
        
        List<RentRequestEntity> entities = entityManager.createQuery(cq).getResultList();

        return entities.stream()
            .map(entity -> {
                GetRentRequestSpecialistsDashboardDTO dto = new GetRentRequestSpecialistsDashboardDTO();
                dto.setRentRequestId(entity.getId());
                dto.setTenantName(entity.getTenant().getFullName());
                dto.setClinicName(entity.getClinic().getDisplayName());
                dto.setStatus(entity.getStatus());
                dto.setCreatedAt(entity.getCreatedAt());
                
                // Set tenant specialty
                if (entity.getTenant().getTenantSpecialty() != null) {
                    dto.setTenantSpecialty(entity.getTenant().getTenantSpecialty().getName());
                }
                
                // Set clinic location information
                dto.setClinicAddress(entity.getClinic().getAddressStreet() + ", " + 
                                   entity.getClinic().getAddressCity());
                dto.setClinicBorough(entity.getClinic().getAddressCity()); // Assuming city is the borough
                dto.setClinicLatitude(Double.parseDouble(entity.getClinic().getAddressLatitude()));
                dto.setClinicLongitude(Double.parseDouble(entity.getClinic().getAddressLongitude()));
                
                return dto;
            })
            .collect(Collectors.toList());
    }
}
