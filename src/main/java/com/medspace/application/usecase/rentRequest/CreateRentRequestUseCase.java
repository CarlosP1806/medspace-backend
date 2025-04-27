package com.medspace.application.usecase.rentRequest;

import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateRentRequestUseCase {

    @Inject
    RentRequestRepository rentRequestRepository;

    @Transactional
    public RentRequest execute(RentRequest rentRequest) {
        return rentRequestRepository.insert(rentRequest);
    }
}
