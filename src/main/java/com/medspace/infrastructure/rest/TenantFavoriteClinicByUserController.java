package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.user.tenantFavoriteClinic.GetFavoriteClinicsByTenantIdUseCase;
import com.medspace.domain.model.TenantFavoriteClinic;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.clinic.TenantFavoriteClinicResponseDTO;
import com.medspace.infrastructure.rest.annotations.TenantOnly;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.rest.context.RequestContext;

import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
@Path("/users/favorite-clinics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TenantFavoriteClinicByUserController {

    @Inject
    GetFavoriteClinicsByTenantIdUseCase getFavoriteClinicsByTenantIdUseCase;
    @Inject
    RequestContext requestContext;

    @GET
    @Path("/me")
    @TenantOnly
    public Response getMyFavoriteClinics() {
        try {
            User loggedUser = requestContext.getUser();
            List<TenantFavoriteClinic> favoriteClinics =
                    getFavoriteClinicsByTenantIdUseCase.execute(loggedUser.getId());
            List<TenantFavoriteClinicResponseDTO> responseDTOs =
                    favoriteClinics.stream().map(TenantFavoriteClinicResponseDTO::fromFavorite)
                            .collect(Collectors.toList());

            return Response.ok(ResponseDTO.success("Favorite clinics fetched", responseDTOs))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

}
