package com.medspace.application.service;

import java.time.Instant;
import java.util.List;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.domain.repository.RentRequestDayRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RentRequestDayService {
    @Inject
    RentRequestDayRepository rentRequestDayRepository;

    public RentRequestDay createRentRequestDay(RentRequestDay rentRequestDay, Long rentRequestId) {
        rentRequestDay.setCreatedAt(Instant.now());
        rentRequestDay =
                rentRequestDayRepository.insertRentRequestDay(rentRequestDay, rentRequestId);
        return rentRequestDay;
    }

    public List<RentRequestDay> listRentRequestDaysByRentRequestId(Long rentRequestId) {
        return rentRequestDayRepository.getRentRequestDaysByRentRequestId(rentRequestId);
    }
}
