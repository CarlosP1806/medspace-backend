package com.medspace.application.usecase.rentRequest;

import com.medspace.domain.repository.RentRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteRentRequestUseCaseImpl implements DeleteRentRequestUseCase {

    @Inject
    RentRequestRepository repository;

    @Override
    @Transactional
    public void execute(Long id) {
        repository.deleteById(id);
    }
}
