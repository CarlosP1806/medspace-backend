package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinic.availability.AssignAvailabilityToClinicUseCase;
import com.medspace.application.usecase.clinic.availability.CreateClinicAvailabilityUseCase;
import com.medspace.application.usecase.clinic.availability.DeleteClinicAvailabilityByIdUseCase;
import com.medspace.application.usecase.clinic.availability.UpdateClinicAvailabilityUseCase;
import com.medspace.domain.model.ClinicAvailability;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.clinic.CreateClinicAvailabilityDTO;
import com.medspace.infrastructure.dto.clinic.UpdateClinicAvailabilityDTO;
import com.medspace.infrastructure.rest.annotations.LandlordOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
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

    @Inject
    RequestContext requestContext;

    @POST
    @Transactional
    @LandlordOnly
    public Response createClinicAvailability(
            @Valid CreateClinicAvailabilityDTO availabilityRequest) {
        try {
            User loggedUser = requestContext.getUser();

            ClinicAvailability clinicAvailability = createClinicAvailabilityUseCase
                    .execute(availabilityRequest.toClinicAvailability());
            assignAvailabilityToClinicUseCase.execute(clinicAvailability.getId(),
                    availabilityRequest.getClinicId(), loggedUser.getId());

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("ClinicAvailability Created")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @LandlordOnly
    public Response deleteClinicAvailabilityById(@PathParam("id") Long id) {
        try {
            User loggedUser = requestContext.getUser();

            deleteClinicAvailabilityByIdUseCase.execute(id, loggedUser.getId());
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
    @LandlordOnly
    public Response updateClinicAvailabilityById(@PathParam("id") Long id,
            @Valid UpdateClinicAvailabilityDTO availabilityRequest) {
        try {
            User loggedUser = requestContext.getUser();

            updateClinicAvailabilityUseCase.execute(id, availabilityRequest.toClinicAvailability(),
                    loggedUser.getId());
            return Response.ok(ResponseDTO.success("ClinicAvailability Updated")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }
}
