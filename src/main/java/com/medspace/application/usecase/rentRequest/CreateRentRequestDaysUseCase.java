package com.medspace.application.usecase.rentRequest;

import java.util.List;
import com.medspace.application.service.RentRequestDayService;
import com.medspace.domain.model.RentRequestDay;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateRentRequestDaysUseCase {
    @Inject
    RentRequestDayService rentRequestDayService;

    @Transactional
    public void execute(List<RentRequestDay> rentRequestDays, Long rentRequestId) {
        for (RentRequestDay rentRequestDay : rentRequestDays) {
            rentRequestDayService.createRentRequestDay(rentRequestDay, rentRequestId);
        }
    }
}
