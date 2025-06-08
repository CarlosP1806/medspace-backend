package com.medspace.application.usecase.clinic;
import java.util.Set;
import com.medspace.application.service.ClinicService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped

public class GetAllCitiesWithClinicsUseCase {
    @Inject
    ClinicService clinicService;
     
    public Set<String> execute(){
        return clinicService.findAllUniqueCities() ;
    }
    
}
