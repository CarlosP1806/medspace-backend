package com.medspace.infrastructure.repository;

import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.infrastructure.entity.RentRequestEntity;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.dto.rentRequest.RentRequestQueryFilterDTO;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.mapper.RentRequestMapper;
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
        predicates.add(cb.equal(clinicJoin.get("landlord").get("id"), filterDTO.getLandlordId()));

        if (filterDTO.getStatus() != null) {
            predicates.add(cb.equal(root.get("status"), filterDTO.getStatus()));
        }

        cq.select(root).distinct(true).where(cb.and(predicates.toArray(new Predicate[0])));
        List<RentRequestEntity> entities = entityManager.createQuery(cq).getResultList();
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
}
