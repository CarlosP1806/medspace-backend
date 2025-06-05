package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.ExternalClinic;
import com.medspace.infrastructure.dto.externalClinic.ExternalClinicResponseDTO;
import com.medspace.infrastructure.util.SpecialtyDetector;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalClinicResponseMapper {

    public static ExternalClinic toDomain(ExternalClinicResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        ExternalClinic clinic = new ExternalClinic();
        clinic.setClee(dto.getClee());
        clinic.setExternalId(dto.getId());
        clinic.setNombre(dto.getNombre());
        clinic.setRazonSocial(dto.getRazon_social());
        clinic.setClaseActividad(dto.getClase_actividad());
        clinic.setEstrato(dto.getEstrato());
        clinic.setTipoVialidad(dto.getTipo_vialidad());
        clinic.setCalle(dto.getCalle());
        clinic.setNumExterior(dto.getNum_Exterior());
        clinic.setNumInterior(dto.getNum_Interior());
        clinic.setColonia(dto.getColonia());
        clinic.setCp(dto.getCp());
        clinic.setUbicacion(dto.getUbicacion());
        clinic.setTelefono(dto.getTelefono());
        clinic.setCorreoE(dto.getCorreo_e());
        clinic.setSitioInternet(dto.getSitio_internet());
        clinic.setTipo(dto.getTipo());
        clinic.setSpecialty(SpecialtyDetector.detectSpecialty(dto.getNombre()));

        // Convert String coordinates to Double
        try {
            if (dto.getLatitud() != null && !dto.getLatitud().isEmpty()) {
                clinic.setLatitud(Double.parseDouble(dto.getLatitud()));
            }
            if (dto.getLongitud() != null && !dto.getLongitud().isEmpty()) {
                clinic.setLongitud(Double.parseDouble(dto.getLongitud()));
            }
        } catch (NumberFormatException e) {
            // Si hay error en la conversión, dejamos las coordenadas como null
        }

        clinic.setTipoCorredorIndustrial(dto.getTipo_corredor_industrial());
        clinic.setNomCorredorIndustrial(dto.getNom_corredor_industrial());
        clinic.setNumeroLocal(dto.getNumero_local());

        return clinic;
    }

    public static List<ExternalClinic> toDomainList(List<ExternalClinicResponseDTO> dtos) {
        if (dtos == null)
            return List.of();
        return dtos.stream().map(ExternalClinicResponseMapper::toDomain)
                .collect(Collectors.toList());
    }
}
