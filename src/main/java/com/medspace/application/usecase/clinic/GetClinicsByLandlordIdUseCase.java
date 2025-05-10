package com.medspace.application.usecase.clinic;

import java.util.ArrayList;
import java.util.List;
import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.ClinicPhoto;
import com.medspace.infrastructure.dto.clinic.GetClinicPhotoDTO;
import com.medspace.infrastructure.dto.clinic.MyClinicDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetClinicsByLandlordIdUseCase {
    @Inject
    ClinicService clinicService;

    public List<MyClinicDTO> execute(Long landlordId) {
        List<Clinic> clinics = clinicService.getClinicsByLandlordId(landlordId);
        List<MyClinicDTO> myClinics = new ArrayList<>();

        for (Clinic clinic : clinics) {
            List<ClinicPhoto> clinicPhotos = clinicService.listPhotosByClinicId(clinic.getId());

            // Find primary photo or use the first one if available
            ClinicPhoto mainPhoto = null;
            if (!clinicPhotos.isEmpty()) {
                // First try to find the primary photo
                for (ClinicPhoto photo : clinicPhotos) {
                    if (photo.getIsPrimary()) {
                        mainPhoto = photo;
                        break;
                    }
                }

                // If no primary photo found, use the first one
                if (mainPhoto == null) {
                    mainPhoto = clinicPhotos.get(0);
                }
            }

            myClinics.add(new MyClinicDTO(clinic, new GetClinicPhotoDTO(mainPhoto)));
        }

        return myClinics;
    }
}
