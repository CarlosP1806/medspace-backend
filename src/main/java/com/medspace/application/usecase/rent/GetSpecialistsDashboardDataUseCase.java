package com.medspace.application.usecase.rent;

import java.util.List;
import com.medspace.application.service.RentService;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestSpecialistsDashboardDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetSpecialistsDashboardDataUseCase {
    @Inject
    RentService rentService;

    public List<GetRentRequestSpecialistsDashboardDTO> execute() {
        return rentService.getSpecialistsDashboardData();
    }
} 