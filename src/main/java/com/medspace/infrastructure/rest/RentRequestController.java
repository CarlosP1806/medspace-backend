package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.rentRequest.CreateRentRequestUseCase;
import com.medspace.application.usecase.rentRequest.ListRentRequestUseCase;
import com.medspace.application.usecase.rentRequest.DeleteRentRequestUseCase;
import com.medspace.domain.model.RentRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/rent-requests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RentRequestController {

    @Inject
    CreateRentRequestUseCase createRentRequest;

    @Inject
    ListRentRequestUseCase listRentRequests;

    @Inject
    DeleteRentRequestUseCase deleteRentRequest;

    @GET
    public Response getAll() {
        List<RentRequest> requests = listRentRequests.execute();
        return Response.ok(new GenericResponse(true, "Rent requests fetched", requests)).build();
    }

    @POST
    public Response create(RentRequest rentRequest) {
        RentRequest created = createRentRequest.execute(rentRequest);
        return Response.status(Response.Status.CREATED)
                .entity(new GenericResponse(true, "Rent request created", created)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        deleteRentRequest.execute(id);
        return Response.ok(new GenericResponse(true, "Rent request deleted", null)).build();
    }
}
