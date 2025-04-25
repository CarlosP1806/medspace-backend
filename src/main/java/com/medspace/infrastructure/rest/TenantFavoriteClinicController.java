package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.tenantFavoriteClinic.*;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.TenantFavoriteClinic;
import com.medspace.infrastructure.dto.CreateTenantFavoriteClinicDTO;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.TenantFavoriteClinicResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
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


    @POST
    @Transactional
    public Response createFavoriteClinic(@Valid CreateTenantFavoriteClinicDTO request) {
        try {
            TenantFavoriteClinic favoriteClinic =
                    createTenantFavoriteClinicUseCase.execute(new TenantFavoriteClinic());

            favoriteClinic = assignTenantFavoriteClinicToTenantUseCase
                    .execute(favoriteClinic.getId(), request.getTenantId());

            favoriteClinic = assignTenantFavoriteClinicToClinicUseCase
                    .execute(favoriteClinic.getId(), request.getClinicId());

            TenantFavoriteClinicResponseDTO responseDTO =
                    TenantFavoriteClinicResponseDTO.fromFavorite(favoriteClinic);

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("Favorite clinic added", responseDTO)).build();
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
    @Transactional
    @Path("/tenant/{tenantId}/clinic/{clinicId}")
    public Response removeFavoriteClinic(@PathParam("tenantId") Long tenantId,
            @PathParam("clinicId") Long clinicId) {
        try {
            removeFavoriteClinicUseCase.execute(tenantId, clinicId);
            return Response.ok(ResponseDTO.success("Favorite clinic removed")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }


}
