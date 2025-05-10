package com.medspace.application.usecase.rent;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.RentRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ListRentRequestUseCase {
    @Inject
    RentService rentService;

    public List<RentRequest> execute() {
        return rentService.listAllRentRequests();
    }
}
