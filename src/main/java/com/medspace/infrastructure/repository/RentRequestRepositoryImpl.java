package com.medspace.infrastructure.repository;

import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.infrastructure.entity.RentRequestEntity;
import com.medspace.infrastructure.mapper.RentRequestMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentRequestRepositoryImpl
        implements RentRequestRepository, PanacheRepository<RentRequestEntity> {

    @Override
    @Transactional
    public RentRequest insert(RentRequest rentRequest) {
        RentRequestEntity entity = RentRequestMapper.toEntity(rentRequest);
        persist(entity);
        return RentRequestMapper.toDomain(entity);
    }

    @Override
    public List<RentRequest> findAllRequests() {
        // <-- llamamos directamente a listAll(), que Panache instrumenta
        return listAll().stream().map(RentRequestMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public RentRequest findRequestById(Long id) {
        // <-- findById es el método instrumentado de PanacheRepository
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
