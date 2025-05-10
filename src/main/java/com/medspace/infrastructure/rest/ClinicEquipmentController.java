package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinic.equipment.AssignEquipmentToClinicUseCase;
import com.medspace.application.usecase.clinic.equipment.CreateClinicEquipmentUseCase;
import com.medspace.application.usecase.clinic.equipment.DeleteClinicEquipmentByIdUseCase;
import com.medspace.domain.model.ClinicEquipment;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.clinic.CreateClinicEquipmentDTO;
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
@Path("/clinic-equipments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClinicEquipmentController {
    @Inject
    CreateClinicEquipmentUseCase createClinicEquipmentUseCase;
    @Inject
    AssignEquipmentToClinicUseCase assignEquipmentToClinicUseCase;
    @Inject
    DeleteClinicEquipmentByIdUseCase deleteClinicEquipmentByIdUseCase;

    @Inject
    RequestContext requestContext;

    @POST
    @Transactional
    @LandlordOnly
    public Response createClinicEquipment(@Valid CreateClinicEquipmentDTO equipmentRequest) {
        try {
            User loggedUser = requestContext.getUser();

            ClinicEquipment clinicEquipment =
                    createClinicEquipmentUseCase.execute(equipmentRequest.toClinicEquipment());
            assignEquipmentToClinicUseCase.execute(clinicEquipment.getId(),
                    equipmentRequest.getClinicId(), loggedUser.getId());

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("ClinicEquipment Created")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @LandlordOnly
    public Response deleteClinicEquipmentById(@PathParam("id") Long id) {
        try {
            User loggedUser = requestContext.getUser();

            deleteClinicEquipmentByIdUseCase.execute(id, loggedUser.getId());
            return Response.ok(ResponseDTO.success("ClinicEquipment Deleted")).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }
}
