package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinicPhoto.AssignPhotoToClinicUseCase;
import com.medspace.application.usecase.clinicPhoto.CreateClinicPhotoUseCase;
import com.medspace.application.usecase.clinicPhoto.DeleteClinicPhotoByIdUseCase;
import com.medspace.application.usecase.clinicPhoto.SetPhotoAsPrimaryClinicPhotoUseCase;
import com.medspace.domain.model.ClinicPhoto;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.clinic.CreateClinicPhotoDTO;
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
@Path("/clinic-photos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClinicPhotoController {
    @Inject
    CreateClinicPhotoUseCase createClinicPhotoUseCase;
    @Inject
    AssignPhotoToClinicUseCase assignPhotoToClinicUseCase;
    @Inject
    DeleteClinicPhotoByIdUseCase deleteClinicPhotoByIdUseCase;
    @Inject
    SetPhotoAsPrimaryClinicPhotoUseCase setPhotoAsPrimaryClinicPhotoUseCase;

    @Inject
    RequestContext requestContext;

    @POST
    @Transactional
    @LandlordOnly
    public Response createClinicPhoto(@Valid CreateClinicPhotoDTO photoRequest) {
        try {
            User loggedUser = requestContext.getUser();

            ClinicPhoto clinicPhoto =
                    createClinicPhotoUseCase.execute(photoRequest.toClinicPhoto());
            assignPhotoToClinicUseCase.execute(clinicPhoto.getId(), photoRequest.getClinicId(),
                    loggedUser.getId());

            if (photoRequest.getIsPrimary()) {
                setPhotoAsPrimaryClinicPhotoUseCase.execute(clinicPhoto.getId(),
                        photoRequest.getClinicId(), loggedUser.getId());
            }

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("ClinicPhoto Created")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @LandlordOnly
    public Response deleteClinicPhotoById(@PathParam("id") Long id) {
        try {
            User loggedUser = requestContext.getUser();
            deleteClinicPhotoByIdUseCase.execute(id, loggedUser.getId());
            return Response.ok(ResponseDTO.success("ClinicPhoto Deleted")).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }
}
