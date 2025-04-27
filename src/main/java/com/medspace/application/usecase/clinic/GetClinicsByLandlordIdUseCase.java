package com.medspace.application.usecase.clinic;

import java.util.List;
import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetClinicsByLandlordIdUseCase {
    @Inject
    ClinicService clinicService;

    public List<Clinic> execute(Long landlordId) {
        return clinicService.getClinicsByLandlordId(landlordId);
    }
}
