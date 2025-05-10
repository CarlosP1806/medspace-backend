package com.medspace.application.usecase.rent;

import java.util.List;
import com.medspace.application.service.RentService;
import com.medspace.domain.model.RentRequestDay;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateRentRequestDaysUseCase {
    @Inject
    RentService rentService;

    @Transactional
    public void execute(List<RentRequestDay> rentRequestDays, Long rentRequestId) {
        for (RentRequestDay rentRequestDay : rentRequestDays) {
            rentService.createRentRequestDay(rentRequestDay, rentRequestId);
        }
    }
}
