package com.medspace.infrastructure.dto.externalClinic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExternalClinicResponseDTO {
    @JsonProperty("CLEE")
    private String clee;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("Nombre")
    private String nombre;

    @JsonProperty("Razon_social")
    private String razon_social;

    @JsonProperty("Clase_actividad")
    private String clase_actividad;

    @JsonProperty("Estrato")
    private String estrato;

    @JsonProperty("Tipo_vialidad")
    private String tipo_vialidad;

    @JsonProperty("Calle")
    private String calle;

    @JsonProperty("Num_Exterior")
    private String num_Exterior;

    @JsonProperty("Num_Interior")
    private String num_Interior;

    @JsonProperty("Colonia")
    private String colonia;

    @JsonProperty("CP")
    private String cp;

    @JsonProperty("Ubicacion")
    private String ubicacion;

    @JsonProperty("Telefono")
    private String telefono;

    @JsonProperty("Correo_e")
    private String correo_e;

    @JsonProperty("Sitio_internet")
    private String sitio_internet;

    @JsonProperty("Tipo")
    private String tipo;

    @JsonProperty("Longitud")
    private String longitud;

    @JsonProperty("Latitud")
    private String latitud;

    @JsonProperty("tipo_corredor_industrial")
    private String tipo_corredor_industrial;

    @JsonProperty("nom_corredor_industrial")
    private String nom_corredor_industrial;

    @JsonProperty("numero_local")
    private String numero_local;
}
