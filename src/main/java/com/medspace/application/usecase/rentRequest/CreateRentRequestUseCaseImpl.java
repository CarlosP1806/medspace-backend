package com.medspace.application.usecase.rentRequest;


import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateRentRequestUseCaseImpl implements CreateRentRequestUseCase {

    @Inject
    RentRequestRepository repository;

    @Override
    @Transactional
    public RentRequest execute(RentRequest rentRequest) {
        return repository.insert(rentRequest);
    }
}
