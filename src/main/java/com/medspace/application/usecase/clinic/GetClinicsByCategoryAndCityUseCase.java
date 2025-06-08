package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class GetClinicsByCategoryAndCityUseCase {
    @Inject
    ClinicService clinicService;
    
    public  long execute (Clinic.Category category, String city){
        return clinicService.getByCategoryAndCity(category, city);
    }

}
