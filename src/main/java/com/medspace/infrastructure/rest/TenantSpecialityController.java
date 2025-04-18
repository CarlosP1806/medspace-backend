package com.medspace.infrastructure.rest;


import java.util.List;
import com.medspace.application.usecase.GetAllTenantSpecialitiesUseCase;
import com.medspace.application.usecase.GetTenantSpecialityByIdUseCase;
import com.medspace.domain.model.TenantSpeciality;
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
@Path("/tenant-specialities")
@Consumes("application/json")
@Produces("application/json")
public class TenantSpecialityController {

    @Inject
    GetTenantSpecialityByIdUseCase getTenantSpecialityByIdUseCase;


    @Inject
    GetAllTenantSpecialitiesUseCase getAllTenantSpecialitiesUseCase;


    @GET
    @Path("/{id}")
    public Response getTenantSpecialityById(@PathParam("id") Long id) {
        try {
            TenantSpeciality tenantSpeciality = getTenantSpecialityByIdUseCase.execute(id);
            if (tenantSpeciality == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error("Tenant speciality with id " + id + " not found"))
                        .build();
            }
            return Response.ok(ResponseDTO.success("Fetched tenantSpeciality", tenantSpeciality))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/")
    public Response getAllTenantSpecialities() {
        try {
            List<TenantSpeciality> tenantSpecialities = getAllTenantSpecialitiesUseCase.execute();
            return Response
                    .ok(ResponseDTO.success("Fetched Tenant Specialities", tenantSpecialities))
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

}
