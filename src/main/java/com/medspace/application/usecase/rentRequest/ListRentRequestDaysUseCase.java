package com.medspace.application.usecase.rentRequest;

import java.util.List;
import com.medspace.application.service.RentRequestDayService;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestDayDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ListRentRequestDaysUseCase {
    @Inject
    RentRequestDayService rentRequestDayService;

    public List<GetRentRequestDayDTO> execute(Long rentRequestId) {
        List<RentRequestDay> rentRequestDays =
                rentRequestDayService.listRentRequestDaysByRentRequestId(rentRequestId);

        return rentRequestDays.stream().map(GetRentRequestDayDTO::new).toList();
    }

}
