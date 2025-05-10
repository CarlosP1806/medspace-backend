package com.medspace.application.usecase.clinic;

import java.util.List;
import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import com.medspace.infrastructure.dto.clinic.ClinicQueryDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicAvailabilityDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicEquipmentDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicPhotoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetFilteredClinicsUseCase {
    @Inject
    ClinicService clinicService;

    public List<GetClinicDTO> execute(ClinicQueryDTO queryFilterDTO) {
        List<Clinic> clinics = clinicService.getFilteredClinics(queryFilterDTO);
        return clinics.stream().map(clinic -> {
            Double averageRating = clinicService.getAverageRatingById(clinic.getId());

            List<GetClinicPhotoDTO> photoDTOs =
                    queryFilterDTO.getIncludePhotos()
                            ? clinicService.listPhotosByClinicId(clinic.getId()).stream()
                                    .map(GetClinicPhotoDTO::new).toList()
                            : null;

            List<GetClinicEquipmentDTO> equipmentDTOs =
                    queryFilterDTO.getIncludeEquipments()
                            ? clinicService.getEquipmentsByClinicId(clinic.getId()).stream()
                                    .map(GetClinicEquipmentDTO::new).toList()
                            : null;

            List<GetClinicAvailabilityDTO> availabilityDTOs =
                    queryFilterDTO.getIncludeAvailabilities()
                            ? clinicService.getAvailabilitiesByClinicId(clinic.getId()).stream()
                                    .map(GetClinicAvailabilityDTO::new).toList()
                            : null;

            return new GetClinicDTO(clinic, averageRating, photoDTOs, equipmentDTOs,
                    availabilityDTOs);
        }).toList();
    }
}
