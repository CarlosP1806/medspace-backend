package com.medspace.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Entity
@Table(name = "external_clinics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalClinicEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clee", length = 50)
    private String clee;

    @Column(name = "external_id", length = 50)
    private String externalId;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "razon_social", length = 255)
    private String razonSocial;

    @Column(name = "clase_actividad", length = 100)
    private String claseActividad;

    @Column(name = "estrato", length = 50)
    private String estrato;

    @Column(name = "tipo_vialidad", length = 50)
    private String tipoVialidad;

    @Column(name = "calle", length = 255)
    private String calle;

    @Column(name = "num_exterior", length = 20)
    private String numExterior;

    @Column(name = "num_interior", length = 20)
    private String numInterior;

    @Column(name = "colonia", length = 255)
    private String colonia;

    @Column(name = "cp", length = 10)
    private String cp;

    @Column(name = "ubicacion", length = 255)
    private String ubicacion;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "correo_e", length = 255)
    private String correoE;

    @Column(name = "sitio_internet", length = 255)
    private String sitioInternet;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "tipo_corredor_industrial", length = 100)
    private String tipoCorredorIndustrial;

    @Column(name = "nom_corredor_industrial", length = 255)
    private String nomCorredorIndustrial;

    @Column(name = "numero_local", length = 50)
    private String numeroLocal;

    @Column(name = "specialty", length = 100)
    private String specialty;

    @Column(name = "created_at")
    private Instant createdAt;
}
