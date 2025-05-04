package com.medspace.application.usecase.clinic;

import com.medspace.application.service.ClinicAvailabilityService;
import com.medspace.application.service.ClinicEquipmentService;
import com.medspace.application.service.ClinicPhotoService;
import com.medspace.application.service.ClinicService;
import com.medspace.domain.model.Clinic;
import com.medspace.infrastructure.dto.clinic.ClinicQueryFilterDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicAvailabilityDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicEquipmentDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicPhotoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetAllClinicsUseCase {
        @Inject
        ClinicService clinicService;
        @Inject
        ClinicPhotoService clinicPhotoService;
        @Inject
        ClinicEquipmentService clinicEquipmentService;
        @Inject
        ClinicAvailabilityService clinicAvailabilityService;

        public List<GetClinicDTO> execute(ClinicQueryFilterDTO queryFilterDTO) {
                List<Clinic> clinics = clinicService.getAllClinics();
                return clinics.stream().map(clinic -> {
                        Double averageRating = clinicService.getAverageRatingById(clinic.getId());

                        List<GetClinicPhotoDTO> photoDTOs =
                                        queryFilterDTO.getIncludePhotos()
                                                        ? clinicPhotoService
                                                                        .listPhotosByClinicId(clinic
                                                                                        .getId())
                                                                        .stream()
                                                                        .map(GetClinicPhotoDTO::new)
                                                                        .toList()
                                                        : null;

                        List<GetClinicEquipmentDTO> equipmentDTOs =
                                        queryFilterDTO.getIncludeEquipments()
                                                        ? clinicEquipmentService
                                                                        .getEquipmentsByClinicId(
                                                                                        clinic.getId())
                                                                        .stream()
                                                                        .map(GetClinicEquipmentDTO::new)
                                                                        .toList()
                                                        : null;

                        List<GetClinicAvailabilityDTO> availabilityDTOs =
                                        queryFilterDTO.getIncludeAvailabilities()
                                                        ? clinicAvailabilityService
                                                                        .getAvailabilitiesByClinicId(
                                                                                        clinic.getId())
                                                                        .stream()
                                                                        .map(GetClinicAvailabilityDTO::new)
                                                                        .toList()
                                                        : null;

                        return new GetClinicDTO(clinic, averageRating, photoDTOs, equipmentDTOs,
                                        availabilityDTOs);
                }).toList();
        }
}
