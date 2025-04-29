package com.medspace.application.usecase.rentRequest;

import com.medspace.domain.repository.RentRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteRentRequestUseCase {

    @Inject
    RentRequestRepository rentRequestRepository;

    @Transactional
    public void execute(Long id) {
        rentRequestRepository.deleteById(id);
    }
}
