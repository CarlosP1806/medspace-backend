package com.medspace.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalClinic {
    private String clee;
    private String externalId;
    private String nombre;
    private String razonSocial;
    private String claseActividad;
    private String estrato;
    private String tipoVialidad;
    private String calle;
    private String numExterior;
    private String numInterior;
    private String colonia;
    private String cp;
    private String ubicacion;
    private String telefono;
    private String correoE;
    private String sitioInternet;
    private String tipo;
    private Double longitud;
    private Double latitud;
    private String tipoCorredorIndustrial;
    private String nomCorredorIndustrial;
    private String numeroLocal;
    private String specialty;
    private Instant createdAt;
}
