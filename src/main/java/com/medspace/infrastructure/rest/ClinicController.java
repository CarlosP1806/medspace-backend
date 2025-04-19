package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinic.*;
import com.medspace.application.usecase.clinicPhoto.GetPhotosByClinicIdUseCase;
import com.medspace.domain.model.Clinic;
import com.medspace.infrastructure.dto.CreateClinicDTO;
import com.medspace.infrastructure.dto.GetPhotoByClinicIdDTO;
import com.medspace.infrastructure.dto.ResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/clinics")
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
    @Inject
    AssignClinicToUserUseCase assignClinicToUserUseCase;
    @Inject
    GetPhotosByClinicIdUseCase getPhotosByClinicIdUseCase;

    @POST
    @Transactional
    public Response createClinic(@Valid CreateClinicDTO clinicRequest) {
        try {
            Clinic createdClinic = createClinicUseCase.execute(clinicRequest.toClinic());
            assignClinicToUserUseCase.execute(createdClinic.getId(), clinicRequest.getUserId());

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("Clinic Created"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage()))
                    .build();
        }
    }

    @GET
    public Response getAllClinics() {
        try {
            List<Clinic> clinics = getAllClinicsUseCase.execute();
            return Response
                    .ok(ResponseDTO.success("Clinics Fetched", clinics))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage()))
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
                        .entity(ResponseDTO.error("Clinic not Found"))
                        .build();
            }
            return Response
                    .ok(ResponseDTO.success("Clinic Fetched", clinic))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClinicById(@PathParam("id") Long id) {
        try {
            deleteClinicByIdUseCase.execute(id);
            return Response
                    .ok(ResponseDTO.success("Clinic Deleted"))
                    .build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseDTO.error(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/{id}/photos")
    public Response getPhotosByClinicId(@PathParam("id") Long id) {
        try {
            List<GetPhotoByClinicIdDTO> clinicPhotos = getPhotosByClinicIdUseCase.execute(id);

            return Response
                    .ok(ResponseDTO.success("ClinicPhoto Fetched", clinicPhotos))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage()))
                    .build();
        }
    }
}
