package com.medspace.infrastructure.rest;


import java.util.List;
import com.medspace.application.usecase.tenantSpecialties.GetAllTenantSpecialtiesUseCase;
import com.medspace.application.usecase.tenantSpecialties.GetTenantSpecialtyByIdUseCase;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.infrastructure.dto.ResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/tenant-specialties")
@Consumes("application/json")
@Produces("application/json")
public class TenantSpecialtyController {

    @Inject
    GetTenantSpecialtyByIdUseCase getTenantSpecialtyByIdUseCase;


    @Inject
    GetAllTenantSpecialtiesUseCase getAllTenantSpecialtiesUseCase;


    @GET
    @Path("/{id}")
    public Response getTenantSpecialtyById(@PathParam("id") Long id) {
        try {
            TenantSpecialty tenantSpecialty = getTenantSpecialtyByIdUseCase.execute(id);
            if (tenantSpecialty == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error("Tenant Specialty with id " + id + " not found"))
                        .build();
            }
            return Response.ok(ResponseDTO.success("Fetched tenantSpecialty", tenantSpecialty))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/")
    public Response getAllTenantSpecialties() {
        try {
            List<TenantSpecialty> tenantSpecialties = getAllTenantSpecialtiesUseCase.execute();
            return Response.ok(ResponseDTO.success("Fetched Tenant Specialties", tenantSpecialties))
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

}
