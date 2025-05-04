package com.medspace.application.usecase.rentRequest;

import java.util.List;
import com.medspace.application.service.ClinicService;
import com.medspace.application.service.UserService;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestPreviewDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetRentRequestsByLandlordIdUseCase {
    @Inject
    RentRequestRepository rentRequestRepository;
    @Inject
    UserService userService;
    @Inject
    ClinicService clinicService;

    public List<GetRentRequestPreviewDTO> execute(Long landlordId) {
        List<RentRequest> rentRequests = rentRequestRepository.findByLandlordId(landlordId);

        return rentRequests.stream().map(rentRequest -> {
            User tenant = userService.getUserById(rentRequest.getTenant().getId());
            Clinic clinic = clinicService.getClinicById(rentRequest.getClinic().getId());
            return new GetRentRequestPreviewDTO(rentRequest, clinic, tenant);
        }).toList();
    }
}
