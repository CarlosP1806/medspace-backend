package com.medspace.application.usecase.rentRequest;

import java.util.List;
import com.medspace.application.service.RentService;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.RentRequestDay;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateRentRequestUseCase {
    @Inject
    RentService rentService;

    @Transactional
    public RentRequest execute(RentRequest rentRequest, Long tenantId, Long clinicId,
            List<RentRequestDay> dates) {
        RentRequest createdRequest = rentService.createRentRequest(rentRequest, tenantId, clinicId);

        dates.forEach(date -> {
            rentService.createRentRequestDay(date, createdRequest.getId());
        });

        return createdRequest;
    }
}
