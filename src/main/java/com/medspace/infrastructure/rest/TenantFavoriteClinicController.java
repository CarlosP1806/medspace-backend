package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.tenantFavoriteClinic.*;
import com.medspace.domain.model.TenantFavoriteClinic;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.clinic.CreateTenantFavoriteClinicDTO;
import com.medspace.infrastructure.dto.clinic.TenantFavoriteClinicResponseDTO;
import com.medspace.infrastructure.rest.annotations.TenantOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import jakarta.enterprise.context.ApplicationScoped;
import com.medspace.domain.model.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/favorite-clinics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TenantFavoriteClinicController {

    @Inject
    CreateTenantFavoriteClinicUseCase createTenantFavoriteClinicUseCase;

    @Inject
    GetFavoriteClinicByIdUseCase getFavoriteClinicByIdUseCase;

    @Inject
    GetFavoriteClinicsByTenantIdUseCase getFavoriteClinicsByTenantIdUseCase;

    @Inject
    GetFavoriteClinicsByClinicIdUseCase getFavoriteClinicsByClinicIdUseCase;

    @Inject
    RemoveFavoriteClinicUseCase removeFavoriteClinicUseCase;

    @Inject
    IsFavoriteClinicUseCase isFavoriteClinicUseCase;

    @Inject
    AssignTenantFavoriteClinicToClinicUseCase assignTenantFavoriteClinicToClinicUseCase;

    @Inject
    AssignTenantFavoriteClinicToTenantUseCase assignTenantFavoriteClinicToTenantUseCase;

    @Inject
    RequestContext requestContext;

    @POST
    @Transactional
    @TenantOnly
    public Response createTenantFavoriteClinic(
            @Valid CreateTenantFavoriteClinicDTO favoriteClinicRequest) {
        try {
            User loggedUser = requestContext.getUser();
            System.out.println("Clinic ID: " + favoriteClinicRequest.getClinicId());
            TenantFavoriteClinic favoriteClinic = createTenantFavoriteClinicUseCase
                    .execute(favoriteClinicRequest.toDomainModel());
            assignTenantFavoriteClinicToTenantUseCase.execute(favoriteClinic.getId(),
                    loggedUser.getId());
            assignTenantFavoriteClinicToClinicUseCase.execute(favoriteClinic.getId(),
                    favoriteClinicRequest.getClinicId());

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("TenantFavoriteClinic Created")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getFavoriteClinicById(@PathParam("id") Long id) {
        try {
            TenantFavoriteClinic favoriteClinic = getFavoriteClinicByIdUseCase.execute(id);
            if (favoriteClinic == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error("Favorite clinic not found")).build();
            }

            TenantFavoriteClinicResponseDTO responseDTO =
                    TenantFavoriteClinicResponseDTO.fromFavorite(favoriteClinic);
            return Response.ok(ResponseDTO.success("Favorite clinic fetched", responseDTO)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }



    @GET
    @Path("/clinic/{clinicId}")
    public Response getFavoriteClinicsByClinicId(@PathParam("clinicId") Long clinicId) {
        try {
            List<TenantFavoriteClinic> favorites =
                    getFavoriteClinicsByClinicIdUseCase.execute(clinicId);
            List<TenantFavoriteClinicResponseDTO> responseDTOs =
                    favorites.stream().map(TenantFavoriteClinicResponseDTO::fromFavorite).toList();

            return Response.ok(ResponseDTO.success("Favorites fetched", responseDTOs)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }


    @DELETE
    @Path("/clinic/{clinicId}")
    @Transactional
    @TenantOnly
    public Response removeFavoriteClinic(@PathParam("clinicId") Long clinicId) {
        try {
            User loggedUser = requestContext.getUser();
            removeFavoriteClinicUseCase.execute(loggedUser.getId(), clinicId);
            return Response.ok(ResponseDTO.success("Favorite clinic removed")).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }



}
