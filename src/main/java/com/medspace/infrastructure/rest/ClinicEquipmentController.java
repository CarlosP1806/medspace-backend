package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinicEquipment.AssignEquipmentToClinicUseCase;
import com.medspace.application.usecase.clinicEquipment.CreateClinicEquipmentUseCase;
import com.medspace.application.usecase.clinicEquipment.DeleteClinicEquipmentByIdUseCase;
import com.medspace.domain.model.ClinicEquipment;
import com.medspace.infrastructure.dto.CreateClinicEquipmentDTO;
import com.medspace.infrastructure.dto.ResponseDTO;
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

    @POST
    @Transactional
    public Response createClinicEquipment(@Valid CreateClinicEquipmentDTO equipmentRequest) {
        try {
            ClinicEquipment clinicEquipment =
                    createClinicEquipmentUseCase.execute(equipmentRequest.toClinicEquipment());
            assignEquipmentToClinicUseCase.execute(clinicEquipment.getId(),
                    equipmentRequest.getClinicId());

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("ClinicEquipment Created")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClinicEquipmentById(@PathParam("id") Long id) {
        try {
            deleteClinicEquipmentByIdUseCase.execute(id);
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
