package com.medspace.infrastructure.repository;

import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.infrastructure.entity.RentRequestEntity;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.mapper.RentRequestMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentRequestRepositoryImpl
        implements RentRequestRepository, PanacheRepository<RentRequestEntity> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public RentRequest insert(RentRequest rentRequest) {
        RentRequestEntity entity = RentRequestMapper.toEntity(rentRequest);

        if (rentRequest.getTenant() != null && rentRequest.getTenant().getId() != null) {
            UserEntity tenant =
                    entityManager.find(UserEntity.class, rentRequest.getTenant().getId());
            if (tenant == null) {
                throw new NotFoundException(
                        "User with id " + rentRequest.getTenant().getId() + " not found");
            }
            entity.setTenant(tenant);
        }

        if (rentRequest.getClinic() != null && rentRequest.getClinic().getId() != null) {
            ClinicEntity clinic =
                    entityManager.find(ClinicEntity.class, rentRequest.getClinic().getId());
            if (clinic == null) {
                throw new NotFoundException(
                        "Clinic with id " + rentRequest.getClinic().getId() + " not found");
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
}
