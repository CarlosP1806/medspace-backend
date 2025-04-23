package com.medspace.application.usecase.rentRequest;

import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ListRentRequestUseCaseImpl implements ListRentRequestUseCase {

    @Inject
    RentRequestRepository repository;

    @Override
    public List<RentRequest> execute() {
        return repository.findAllRequests();
    }
}
