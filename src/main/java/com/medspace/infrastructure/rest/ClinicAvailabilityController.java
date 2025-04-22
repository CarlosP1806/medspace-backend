package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinicAvailability.AssignAvailabilityToClinicUseCase;
import com.medspace.application.usecase.clinicAvailability.CreateClinicAvailabilityUseCase;
import com.medspace.application.usecase.clinicAvailability.DeleteClinicAvailabilityByIdUseCase;
import com.medspace.application.usecase.clinicAvailability.UpdateClinicAvailabilityUseCase;
import com.medspace.domain.model.ClinicAvailability;
import com.medspace.infrastructure.dto.CreateClinicAvailabilityDTO;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.UpdateClinicAvailabilityDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/clinic-availabilities")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClinicAvailabilityController {
    @Inject
    CreateClinicAvailabilityUseCase createClinicAvailabilityUseCase;
    @Inject
    AssignAvailabilityToClinicUseCase assignAvailabilityToClinicUseCase;
    @Inject
    DeleteClinicAvailabilityByIdUseCase deleteClinicAvailabilityByIdUseCase;
    @Inject
    UpdateClinicAvailabilityUseCase updateClinicAvailabilityUseCase;

    @POST
    @Transactional
    public Response createClinicAvailability(
            @Valid CreateClinicAvailabilityDTO availabilityRequest) {
        try {
            ClinicAvailability clinicAvailability = createClinicAvailabilityUseCase
                    .execute(availabilityRequest.toClinicAvailability());
            assignAvailabilityToClinicUseCase.execute(clinicAvailability.getId(),
                    availabilityRequest.getClinicId());

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("ClinicAvailability Created")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClinicAvailabilityById(@PathParam("id") Long id) {
        try {
            deleteClinicAvailabilityByIdUseCase.execute(id);
            return Response.ok(ResponseDTO.success("ClinicAvailability Deleted")).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateClinicAvailabilityById(@PathParam("id") Long id,
            @Valid UpdateClinicAvailabilityDTO availabilityRequest) {
        try {
            updateClinicAvailabilityUseCase.execute(id, availabilityRequest.toClinicAvailability());
            return Response.ok(ResponseDTO.success("ClinicAvailability Updated")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }
}
