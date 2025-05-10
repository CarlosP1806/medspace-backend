package com.medspace.application.usecase.rent;

import java.util.List;
import com.medspace.application.service.RentService;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestDayDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListRentRequestDaysUseCase {
    @Inject
    RentService rentService;

    public List<GetRentRequestDayDTO> execute(Long rentRequestId) {
        List<RentRequestDay> rentRequestDays =
                rentService.listRentRequestDaysByRentRequestId(rentRequestId);

        return rentRequestDays.stream().map(GetRentRequestDayDTO::new).toList();
    }

}
