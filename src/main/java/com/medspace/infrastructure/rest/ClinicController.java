package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinic.CreateClinicUseCase;
import com.medspace.application.usecase.clinic.DeleteClinicByIdUseCase;
import com.medspace.application.usecase.clinic.GetAllClinicsUseCase;
import com.medspace.application.usecase.clinic.GetClinicByIdUseCase;
import com.medspace.domain.model.Clinic;
import com.medspace.infrastructure.dto.CreateClinicDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@ApplicationScoped
@Path("clinics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClinicController {
    @Inject
    CreateClinicUseCase createClinicUseCase;
    @Inject
    GetAllClinicsUseCase getAllClinicsUseCase;
    @Inject
    GetClinicByIdUseCase getClinicByIdUseCase;
    @Inject
    DeleteClinicByIdUseCase deleteClinicByIdUseCase;

    @POST
    public Response createClinic(@Valid CreateClinicDTO clinicRequest) {
        try {
            Clinic createdClinic = createClinicUseCase.execute(clinicRequest.toClinic());
            return Response.status(Response.Status.CREATED)
                    .entity(createdClinic)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @GET
    public Response getAllClinics() {
        try {
            List<Clinic> clinics = getAllClinicsUseCase.execute();
            return Response.ok(clinics).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getClinicById(@PathParam("id") Long id) {
        try {
            Clinic clinic = getClinicByIdUseCase.execute(id);
            if (clinic == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(Map.of("error", "clinic not found"))
                        .build();
            }
            return Response.ok(clinic).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClinicById(@PathParam("id") Long id) {
        try {
            deleteClinicByIdUseCase.execute(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }
}
