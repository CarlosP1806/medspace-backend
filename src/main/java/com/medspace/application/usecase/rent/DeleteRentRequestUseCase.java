package com.medspace.application.usecase.rent;

import com.medspace.application.service.RentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeleteRentRequestUseCase {
    @Inject
    RentService rentService;

    @Transactional
    public void execute(Long id) {
        rentService.deleteRentRequestById(id);
    }
}
