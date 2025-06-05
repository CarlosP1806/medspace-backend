package com.medspace.infrastructure.mapper;

import com.medspace.domain.model.ExternalClinic;
import com.medspace.infrastructure.dto.externalClinic.GetExternalClinicSpecialistsDashboardDTO;
import com.medspace.infrastructure.entity.ExternalClinicEntity;
import com.medspace.infrastructure.dto.externalClinic.ExternalClinicResponseDTO;
import com.medspace.infrastructure.util.SpecialtyDetector;
import java.time.Instant;

public class ExternalClinicMapper {

    public static ExternalClinic toDomain(ExternalClinicEntity entity) {
        if (entity == null) {
            return null;
        }

        ExternalClinic clinic = new ExternalClinic();
        clinic.setClee(entity.getClee());
        clinic.setExternalId(entity.getExternalId());
        clinic.setNombre(entity.getNombre());
        clinic.setRazonSocial(entity.getRazonSocial());
        clinic.setClaseActividad(entity.getClaseActividad());
        clinic.setEstrato(entity.getEstrato());
        clinic.setTipoVialidad(entity.getTipoVialidad());
        clinic.setCalle(entity.getCalle());
        clinic.setNumExterior(entity.getNumExterior());
        clinic.setNumInterior(entity.getNumInterior());
        clinic.setColonia(entity.getColonia());
        clinic.setCp(entity.getCp());
        clinic.setUbicacion(entity.getUbicacion());
        clinic.setTelefono(entity.getTelefono());
        clinic.setCorreoE(entity.getCorreoE());
        clinic.setSitioInternet(entity.getSitioInternet());
        clinic.setTipo(entity.getTipo());
        clinic.setLongitud(entity.getLongitud());
        clinic.setLatitud(entity.getLatitud());
        clinic.setTipoCorredorIndustrial(entity.getTipoCorredorIndustrial());
        clinic.setNomCorredorIndustrial(entity.getNomCorredorIndustrial());
        clinic.setNumeroLocal(entity.getNumeroLocal());
        clinic.setSpecialty(entity.getSpecialty());
        clinic.setCreatedAt(entity.getCreatedAt());
        return clinic;
    }

    public static ExternalClinicEntity toEntity(ExternalClinic domain) {
        if (domain == null) {
            return null;
        }

        ExternalClinicEntity entity = new ExternalClinicEntity();
        entity.setClee(domain.getClee());
        entity.setExternalId(domain.getExternalId());
        entity.setNombre(domain.getNombre());
        entity.setRazonSocial(domain.getRazonSocial());
        entity.setClaseActividad(domain.getClaseActividad());
        entity.setEstrato(domain.getEstrato());
        entity.setTipoVialidad(domain.getTipoVialidad());
        entity.setCalle(domain.getCalle());
        entity.setNumExterior(domain.getNumExterior());
        entity.setNumInterior(domain.getNumInterior());
        entity.setColonia(domain.getColonia());
        entity.setCp(domain.getCp());
        entity.setUbicacion(domain.getUbicacion());
        entity.setTelefono(domain.getTelefono());
        entity.setCorreoE(domain.getCorreoE());
        entity.setSitioInternet(domain.getSitioInternet());
        entity.setTipo(domain.getTipo());
        entity.setLongitud(domain.getLongitud());
        entity.setLatitud(domain.getLatitud());
        entity.setTipoCorredorIndustrial(domain.getTipoCorredorIndustrial());
        entity.setNomCorredorIndustrial(domain.getNomCorredorIndustrial());
        entity.setNumeroLocal(domain.getNumeroLocal());
        entity.setSpecialty(domain.getSpecialty());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }

    public static void updateEntityFromDomain(ExternalClinic domain,
            ExternalClinicEntity entityToUpdate) {
        if (domain == null || entityToUpdate == null) {
            return;
        }

        entityToUpdate.setClee(domain.getClee());
        entityToUpdate.setExternalId(domain.getExternalId());
        entityToUpdate.setNombre(domain.getNombre());
        entityToUpdate.setRazonSocial(domain.getRazonSocial());
        entityToUpdate.setClaseActividad(domain.getClaseActividad());
        entityToUpdate.setEstrato(domain.getEstrato());
        entityToUpdate.setTipoVialidad(domain.getTipoVialidad());
        entityToUpdate.setCalle(domain.getCalle());
        entityToUpdate.setNumExterior(domain.getNumExterior());
        entityToUpdate.setNumInterior(domain.getNumInterior());
        entityToUpdate.setColonia(domain.getColonia());
        entityToUpdate.setCp(domain.getCp());
        entityToUpdate.setUbicacion(domain.getUbicacion());
        entityToUpdate.setTelefono(domain.getTelefono());
        entityToUpdate.setCorreoE(domain.getCorreoE());
        entityToUpdate.setSitioInternet(domain.getSitioInternet());
        entityToUpdate.setTipo(domain.getTipo());
        entityToUpdate.setLongitud(domain.getLongitud());
        entityToUpdate.setLatitud(domain.getLatitud());
        entityToUpdate.setTipoCorredorIndustrial(domain.getTipoCorredorIndustrial());
        entityToUpdate.setNomCorredorIndustrial(domain.getNomCorredorIndustrial());
        entityToUpdate.setNumeroLocal(domain.getNumeroLocal());
        entityToUpdate.setSpecialty(domain.getSpecialty());
    }

    public static GetExternalClinicSpecialistsDashboardDTO toDashboardDTO(
            ExternalClinicEntity entity) {
        if (entity == null) {
            return null;
        }

        // Extract state from ubicacion (last component after the last comma)
        String clinicBorough = "";
        if (entity.getUbicacion() != null && !entity.getUbicacion().trim().isEmpty()) {
            String[] parts = entity.getUbicacion().split(",");
            if (parts.length > 0) {
                clinicBorough = parts[parts.length - 1].trim();
            }
        }

        // Ensure coordinates are not null
        Double latitud = entity.getLatitud() != null ? entity.getLatitud() : 0.0;
        Double longitud = entity.getLongitud() != null ? entity.getLongitud() : 0.0;
        String claseActividad =
                entity.getClaseActividad() != null ? entity.getClaseActividad() : "";
        String specialty = entity.getSpecialty() != null ? entity.getSpecialty()
                : SpecialtyDetector.detectSpecialty(entity.getNombre());

        return new GetExternalClinicSpecialistsDashboardDTO(latitud, longitud, clinicBorough,
                claseActividad, specialty);
    }

    public static ExternalClinicEntity toEntity(ExternalClinicResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        ExternalClinicEntity entity = new ExternalClinicEntity();
        entity.setClee(dto.getClee());
        entity.setExternalId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setRazonSocial(dto.getRazon_social());
        entity.setClaseActividad(dto.getClase_actividad());
        entity.setEstrato(dto.getEstrato());
        entity.setTipoVialidad(dto.getTipo_vialidad());
        entity.setCalle(dto.getCalle());
        entity.setNumExterior(dto.getNum_Exterior());
        entity.setNumInterior(dto.getNum_Interior());
        entity.setColonia(dto.getColonia());
        entity.setCp(dto.getCp());
        entity.setUbicacion(dto.getUbicacion());
        entity.setTelefono(dto.getTelefono());
        entity.setCorreoE(dto.getCorreo_e());
        entity.setSitioInternet(dto.getSitio_internet());
        entity.setTipo(dto.getTipo());
        entity.setSpecialty(SpecialtyDetector.detectSpecialty(dto.getNombre()));

        // Convert String coordinates to Double
        try {
            if (dto.getLatitud() != null && !dto.getLatitud().isEmpty()) {
                entity.setLatitud(Double.parseDouble(dto.getLatitud()));
            }
            if (dto.getLongitud() != null && !dto.getLongitud().isEmpty()) {
                entity.setLongitud(Double.parseDouble(dto.getLongitud()));
            }
        } catch (NumberFormatException e) {
            // Si hay error en la conversión, dejamos las coordenadas como null
        }

        entity.setTipoCorredorIndustrial(dto.getTipo_corredor_industrial());
        entity.setNomCorredorIndustrial(dto.getNom_corredor_industrial());
        entity.setNumeroLocal(dto.getNumero_local());
        entity.setCreatedAt(Instant.now());

        return entity;
    }

    public static ExternalClinicResponseDTO toDTO(ExternalClinicEntity entity) {
        if (entity == null) {
            return null;
        }

        ExternalClinicResponseDTO dto = new ExternalClinicResponseDTO();
        dto.setClee(entity.getClee());
        dto.setId(entity.getExternalId());
        dto.setNombre(entity.getNombre());
        dto.setRazon_social(entity.getRazonSocial());
        dto.setClase_actividad(entity.getClaseActividad());
        dto.setEstrato(entity.getEstrato());
        dto.setTipo_vialidad(entity.getTipoVialidad());
        dto.setCalle(entity.getCalle());
        dto.setNum_Exterior(entity.getNumExterior());
        dto.setNum_Interior(entity.getNumInterior());
        dto.setColonia(entity.getColonia());
        dto.setCp(entity.getCp());
        dto.setUbicacion(entity.getUbicacion());
        dto.setTelefono(entity.getTelefono());
        dto.setCorreo_e(entity.getCorreoE());
        dto.setSitio_internet(entity.getSitioInternet());
        dto.setTipo(entity.getTipo());

        // Convert Double coordinates to String
        if (entity.getLatitud() != null) {
            dto.setLatitud(entity.getLatitud().toString());
        }
        if (entity.getLongitud() != null) {
            dto.setLongitud(entity.getLongitud().toString());
        }

        dto.setTipo_corredor_industrial(entity.getTipoCorredorIndustrial());
        dto.setNom_corredor_industrial(entity.getNomCorredorIndustrial());
        dto.setNumero_local(entity.getNumeroLocal());

        return dto;
    }
}
