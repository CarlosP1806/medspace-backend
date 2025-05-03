package com.medspace.infrastructure.repository;


import com.medspace.domain.model.TenantFavoriteClinic;
import com.medspace.domain.repository.TenantFavoriteClinicRepository;
import com.medspace.infrastructure.entity.ClinicEntity;
import com.medspace.infrastructure.entity.TenantFavoriteClinicEntity;
import com.medspace.infrastructure.entity.UserEntity;
import java.time.Instant;
import com.medspace.infrastructure.mapper.TenantFavoriteClinicMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TenantFavoriteClinicRepositoryImpl implements TenantFavoriteClinicRepository,
        PanacheRepositoryBase<TenantFavoriteClinicEntity, Long> {

    @Inject
    ClinicRepositoryImpl clinicRepository;

    @Inject
    UserRepositoryImpl userRepository;

    @Transactional
    @Override
    public TenantFavoriteClinic createTenantFavoriteClinic(
        TenantFavoriteClinic tenantFavoriteClinic) {
        if (tenantFavoriteClinic.getCreatedAt() == null) {
            tenantFavoriteClinic.setCreatedAt(Instant.now());
        }
        TenantFavoriteClinicEntity tenantFavoriteClinicEntity =
                TenantFavoriteClinicMapper.toEntity(tenantFavoriteClinic);
        persist(tenantFavoriteClinicEntity);
        return TenantFavoriteClinicMapper.toDomain(tenantFavoriteClinicEntity);
    }

    @Override
    public TenantFavoriteClinic getFavoriteClinicById(Long id) {
        TenantFavoriteClinicEntity entity = findById(id);
        if (entity == null) {
            throw new NotFoundException("TenantFavoriteClinic with id " + id + " not found");
        }
        return TenantFavoriteClinicMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public void deleteFavoriteClinicById(Long id) {
        TenantFavoriteClinicEntity entity = findById(id);
        if (entity == null) {
            throw new NotFoundException("TenantFavoriteClinic with id " + id + " not found");
        }
        delete(entity);
    }

    @Override
    public List<TenantFavoriteClinic> getFavoriteClinicsByTenantId(Long tenantId) {
        UserEntity userEntity = userRepository.findById(tenantId);
        if (userEntity == null) {
            throw new NotFoundException("User with id " + tenantId + " not found");
        }

        List<TenantFavoriteClinicEntity> entities = list("tenant.id", tenantId);
        return entities.stream().map(TenantFavoriteClinicMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TenantFavoriteClinic> getFavoriteClinicsByClinicId(Long clinicId) {
        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not found");
        }

        List<TenantFavoriteClinicEntity> entities = list("clinic.id", clinicId);
        return entities.stream().map(TenantFavoriteClinicMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFavoriteClinic(Long tenantId, Long clinicId) {
        return count("tenant.id = ?1 and clinic.id = ?2", tenantId, clinicId) > 0;
    }

    @Override
    @Transactional
    public void removeFavoriteClinic(Long tenantId, Long clinicId) {

        UserEntity userEntity = userRepository.findById(tenantId);
        if (userEntity == null) {
            throw new NotFoundException("User with id " + tenantId + " not found");
        }
        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not found");
        }
        long deletedCount = delete("tenant.id = ?1 and clinic.id = ?2", tenantId, clinicId);
        if (deletedCount == 0) {
            throw new NotFoundException("Favorite clinic relationship not found for tenant "
                    + tenantId + " and clinic " + clinicId);
        }
    }

    @Override
    @Transactional
    public TenantFavoriteClinic assignToTenant(Long favoriteClinicId, Long tenantId) {
        TenantFavoriteClinicEntity entity = findById(favoriteClinicId);
        if (entity == null) {
            throw new NotFoundException(
                    "Favorite Clinic with id " + favoriteClinicId + " not found");
        }
        UserEntity tenantEntity = userRepository.findById(tenantId);
        if (tenantEntity == null) {
            throw new NotFoundException("Tenant with id " + tenantId + " not found");
        }
        entity.setTenant(tenantEntity);
        persist(entity);
        return TenantFavoriteClinicMapper.toDomain(entity);
    }

    @Override
    @Transactional
    public TenantFavoriteClinic assignToClinic(Long favoriteClinicId, Long clinicId) {
        TenantFavoriteClinicEntity entity = findById(favoriteClinicId);
        if (entity == null) {
            throw new NotFoundException(
                    "Favorite Clinic with id " + favoriteClinicId + " not found");
        }
        ClinicEntity clinicEntity = clinicRepository.findById(clinicId);
        if (clinicEntity == null) {
            throw new NotFoundException("Clinic with id " + clinicId + " not found");
        }
        entity.setClinic(clinicEntity);
        persist(entity);
        return TenantFavoriteClinicMapper.toDomain(entity);
    }

}
