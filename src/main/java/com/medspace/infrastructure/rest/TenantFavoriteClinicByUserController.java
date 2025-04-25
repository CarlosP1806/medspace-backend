package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.tenantFavoriteClinic.GetFavoriteClinicsByTenantIdUseCase;
import com.medspace.domain.model.TenantFavoriteClinic;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.TenantFavoriteClinicResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/users/favorite-clinics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TenantFavoriteClinicByUserController {

    @Inject
    GetFavoriteClinicsByTenantIdUseCase getFavoriteClinicsByTenantIdUseCase;

    @GET
    @Path("/{tenantId}")
    public Response getFavoriteClinicsByTenantId(@PathParam("tenantId") Long tenantId) {
        try {
            List<TenantFavoriteClinic> favoriteClinics = getFavoriteClinicsByTenantIdUseCase.execute(tenantId);
            List<TenantFavoriteClinicResponseDTO> responseDTOs = favoriteClinics.stream()
                    .map(TenantFavoriteClinicResponseDTO::fromFavorite)
                    .collect(Collectors.toList());

            return Response.ok(ResponseDTO.success("Favorite clinics fetched", responseDTOs)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }
}
