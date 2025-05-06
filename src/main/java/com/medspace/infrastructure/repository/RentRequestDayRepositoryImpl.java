package com.medspace.infrastructure.repository;

import java.util.List;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.domain.repository.RentRequestDayRepository;
import com.medspace.infrastructure.entity.RentRequestDayEntity;
import com.medspace.infrastructure.entity.RentRequestEntity;
import com.medspace.infrastructure.mapper.RentRequestDayMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RentRequestDayRepositoryImpl
        implements RentRequestDayRepository, PanacheRepositoryBase<RentRequestDayEntity, Long> {

    @Inject
    RentRequestRepositoryImpl rentRequestRepository;

    @Transactional
    public RentRequestDay insertRentRequestDay(RentRequestDay rentRequestDay, Long rentRequestId) {
        System.out.println("Inserting RentRequestDay: " + rentRequestId);
        RentRequestDayEntity rentRequestDayEntity = RentRequestDayMapper.toEntity(rentRequestDay);

        RentRequestEntity rentRequestEntity = rentRequestRepository.findById(rentRequestId);
        if (rentRequestEntity == null) {
            throw new jakarta.ws.rs.NotFoundException(
                    "RentRequest with id " + rentRequestId + " not found");
        }

        rentRequestDayEntity.setRentRequest(rentRequestEntity);
        persist(rentRequestDayEntity);
        rentRequestDay = RentRequestDayMapper.toDomain(rentRequestDayEntity);
        return rentRequestDay;
    }

    public List<RentRequestDay> getRentRequestDaysByRentRequestId(Long rentRequestId) {
        List<RentRequestDayEntity> rentRequestDayEntities = list("rentRequest.id", rentRequestId);
        return rentRequestDayEntities.stream().map(RentRequestDayMapper::toDomain).toList();
    }
}
