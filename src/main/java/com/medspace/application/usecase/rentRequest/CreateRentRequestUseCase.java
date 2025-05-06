package com.medspace.application.usecase.rentRequest;

import java.time.Instant;
import java.util.List;
import com.medspace.application.service.RentRequestDayService;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.domain.repository.RentRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateRentRequestUseCase {

    @Inject
    RentRequestRepository rentRequestRepository;
    @Inject
    RentRequestDayService rentRequestDayService;

    @Transactional
    public RentRequest execute(RentRequest rentRequest, Long tenantId, Long clinicId,
            List<RentRequestDay> dates) {
        rentRequest.setCreatedAt(Instant.now());
        RentRequest createdRequest = rentRequestRepository.insert(rentRequest, tenantId, clinicId);

        dates.forEach(date -> {
            rentRequestDayService.createRentRequestDay(date, createdRequest.getId());
        });

        return createdRequest;
    }
}
