package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.externalClinic.GetExternalClinicsDashboardUseCase;
import com.medspace.application.usecase.externalClinic.LoadExternalClinicsUseCase;
import com.medspace.application.usecase.externalClinic.UpdateExternalClinicSpecialtiesUseCase;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.externalClinic.GetExternalClinicSpecialistsDashboardDTO;
import com.medspace.infrastructure.dto.common.PaginationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Path("/api/external-clinics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExternalClinicController {

    @Inject
    LoadExternalClinicsUseCase loadExternalClinicsUseCase;

    @Inject
    GetExternalClinicsDashboardUseCase getExternalClinicsDashboardUseCase;

    @Inject
    UpdateExternalClinicSpecialtiesUseCase updateExternalClinicSpecialtiesUseCase;

    @POST
    @Path("/load")

    public Response loadClinics() {
        try {
            loadExternalClinicsUseCase.execute();
            return Response.ok(ResponseDTO.success("Clinics loaded successfully")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error("Error loading clinics: " + e.getMessage())).build();
        }
    }

    @GET
    @Path("/dashboard")
    public Response getDashboardData(@QueryParam("page") Integer page,
            @QueryParam("size") Integer size, @QueryParam("all") Boolean getAll) {
        try {
            if (Boolean.TRUE.equals(getAll)) {
                List<GetExternalClinicSpecialistsDashboardDTO> allData =
                        getExternalClinicsDashboardUseCase.executeGetAll();
                return Response.ok(ResponseDTO.success("Dashboard data retrieved successfully",
                        Map.of("totalRecords", allData.size(), "data", allData))).build();
            } else {
                PaginationDTO pagination =
                        new PaginationDTO(page != null ? page : 0, size != null ? size : 50);
                List<GetExternalClinicSpecialistsDashboardDTO> data =
                        getExternalClinicsDashboardUseCase.executeWithPagination(pagination);
                return Response
                        .ok(ResponseDTO.success("Dashboard data retrieved successfully", data))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error("Error retrieving dashboard data: " + e.getMessage()))
                    .build();
        }
    }

    @POST
    @Path("/update-specialties")
    public Response updateSpecialties() {
        try {
            updateExternalClinicSpecialtiesUseCase.execute();
            return Response.ok(ResponseDTO.success("Specialties updated successfully")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error("Error updating specialties: " + e.getMessage()))
                    .build();
        }
    }
}
