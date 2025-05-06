package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.rentRequest.CreateRentRequestDaysUseCase;
import com.medspace.application.usecase.rentRequest.CreateRentRequestUseCase;
import com.medspace.application.usecase.rentRequest.ListRentRequestUseCase;
import com.medspace.application.usecase.rentRequest.DeleteRentRequestUseCase;
import com.medspace.application.usecase.rentRequest.GetRentRequestsByLandlordIdUseCase;
import com.medspace.application.usecase.rentRequest.ListRentRequestDaysUseCase;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.model.RentRequestDay;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.rentRequest.CreateRentRequestDTO;
import com.medspace.infrastructure.dto.rentRequest.CreateRentRequestDaysDTO;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestDTO;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestDayDTO;
import com.medspace.infrastructure.dto.rentRequest.GetRentRequestPreviewDTO;
import com.medspace.infrastructure.dto.rentRequest.RentRequestQueryFilterDTO;
import com.medspace.infrastructure.rest.annotations.LandlordOnly;
import com.medspace.infrastructure.rest.annotations.TenantOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

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
    @Inject
    GetRentRequestsByLandlordIdUseCase getRentRequestsByLandlordId;
    @Inject
    CreateRentRequestDaysUseCase createRentRequestDaysUseCase;
    @Inject
    ListRentRequestDaysUseCase listRentRequestDaysUseCase;

    @Inject
    RequestContext requestContext;

    @GET
    public Response getAll() {
        List<RentRequest> list = listRentRequests.execute();
        List<GetRentRequestDTO> dtos =
                list.stream().map(GetRentRequestDTO::new).collect(Collectors.toList());

        return Response.ok(ResponseDTO.success("Fetched rent-requests", dtos)).build();
    }

    @GET
    @Path("/landlord")
    @LandlordOnly
    public Response getByLandlord(@QueryParam("status") String targetStatus) {
        User loggedInUser = requestContext.getUser();
        RentRequestQueryFilterDTO filterDTO = new RentRequestQueryFilterDTO(loggedInUser.getId());
        if (targetStatus != null) {
            filterDTO.setStatus(targetStatus);
        }

        List<GetRentRequestPreviewDTO> list = getRentRequestsByLandlordId.execute(filterDTO);

        return Response.ok(ResponseDTO.success("Fetched rent-requests", list)).build();
    }

    @POST
    @TenantOnly
    public Response create(@Valid CreateRentRequestDTO dto) {
        try {
            User loggedInUser = requestContext.getUser();
            RentRequest toSave = dto.toModel();
            List<RentRequestDay> dates = dto.getDates().stream().map(date -> {
                RentRequestDay rentRequestDay = new RentRequestDay();
                rentRequestDay.setDate(date);
                return rentRequestDay;
            }).collect(Collectors.toList());

            RentRequest saved = createRentRequest.execute(toSave, loggedInUser.getId(),
                    dto.getClinicId(), dates);
            GetRentRequestDTO out = new GetRentRequestDTO(saved);

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("Created rent-request", out)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        deleteRentRequest.execute(id);
        return Response.ok(ResponseDTO.success("Deleted rent-request")).build();
    }

    @POST
    @Path("/{id}/days")
    @TenantOnly
    public Response createRequestDays(@PathParam("id") Long rentRequestId,
            @Valid CreateRentRequestDaysDTO rentRequestDays) {
        try {
            List<RentRequestDay> rentRequestDayList = rentRequestDays.toRentRequestDays();
            createRentRequestDaysUseCase.execute(rentRequestDayList, rentRequestId);
            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("Created rent-request days")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}/days")
    @LandlordOnly
    public Response getRequestDays(@PathParam("id") Long rentRequestId) {
        try {
            List<GetRentRequestDayDTO> rentRequestDays =
                    listRentRequestDaysUseCase.execute(rentRequestId);

            return Response.ok(ResponseDTO.success("Fetched rent-request days", rentRequestDays))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

}
