package com.medspace.application.usecase.rentRequest;

import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ListRentRequestUseCase {

    @Inject
    RentRequestRepository rentRequestRepository;

    public List<RentRequest> execute() {
        return rentRequestRepository.findAllRequests();
    }
}
