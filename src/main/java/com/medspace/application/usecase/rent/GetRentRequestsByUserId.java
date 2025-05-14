package com.medspace.application.usecase.rent;

import java.sql.Date;
import java.util.List;
import com.medspace.application.service.ClinicService;
import com.medspace.application.service.RentService;
import com.medspace.application.service.UserService;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.ClinicAvailability;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.clinic.GetClinicAvailabilityDTO;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestPreviewDTO;
import com.medspace.infrastructure.dto.rentRequest.RentRequestQueryFilterDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetRentRequestsByUserId {
    @Inject
    RentService rentService;
    @Inject
    UserService userService;
    @Inject
    ClinicService clinicService;

    public List<GetRentRequestPreviewDTO> execute(RentRequestQueryFilterDTO filterDTO) {
        List<RentRequest> rentRequests = rentService.listRentRequestsByUserId(filterDTO);

        return rentRequests.stream().map(rentRequest -> {
            User tenant = userService.getUserById(rentRequest.getTenant().getId());
            Clinic clinic = clinicService.getClinicById(rentRequest.getClinic().getId());
            List<RentRequestDay> requestedDays =
                    rentService.listRentRequestDaysByRentRequestId(rentRequest.getId());

            List<ClinicAvailability> clinicAvailabilities =
                    clinicService.getAvailabilitiesByClinicId(clinic.getId());
            List<GetClinicAvailabilityDTO> clinicAvailabilitiesDTO =
                    clinicAvailabilities.stream().map(GetClinicAvailabilityDTO::new).toList();

            List<Date> requestedDaysAsDate =
                    requestedDays.stream().map(RentRequestDay::getDate).toList();

            String clinicMainPhotoPath = clinicService.getMainPhotoPathByClinicId(clinic.getId());
            return new GetRentRequestPreviewDTO(rentRequest, clinic, tenant, requestedDaysAsDate,
                    clinicMainPhotoPath, clinicAvailabilitiesDTO);
        }).toList();
    }
}
