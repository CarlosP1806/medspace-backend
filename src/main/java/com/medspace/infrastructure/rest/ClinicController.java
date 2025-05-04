package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinic.*;
import com.medspace.application.usecase.clinicAvailability.GetAvailabilitiesByClinicIdUseCase;
import com.medspace.application.usecase.clinicEquipment.GetEquipmentsByClinicIdUseCase;
import com.medspace.application.usecase.clinicPhoto.GetClinicPhotoByIdUseCase;
import com.medspace.application.usecase.clinicPhoto.GetPhotosByClinicIdUseCase;
import com.medspace.application.usecase.clinicPhoto.SetPhotoAsPrimaryClinicPhotoUseCase;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.*;
import com.medspace.infrastructure.dto.clinic.ClinicQueryFilterDTO;
import com.medspace.infrastructure.dto.clinic.CreateClinicDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicAvailabilityDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicEquipmentDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicPhotoDTO;
import com.medspace.infrastructure.dto.clinic.SetPhotoAsPrimaryDTO;
import com.medspace.infrastructure.rest.annotations.LandlordOnly;
import com.medspace.infrastructure.rest.annotations.UserOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

@ApplicationScoped
@Path("/clinics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClinicController {
    @Inject
    CreateClinicUseCase createClinicUseCase;
    @Inject
    GetAllClinicsUseCase getAllClinicsUseCase;
    @Inject
    GetClinicByIdUseCase getClinicByIdUseCase;
    @Inject
    DeleteClinicByIdUseCase deleteClinicByIdUseCase;
    @Inject
    AssignClinicToUserUseCase assignClinicToUserUseCase;
    @Inject
    GetPhotosByClinicIdUseCase getPhotosByClinicIdUseCase;
    @Inject
    SetPhotoAsPrimaryClinicPhotoUseCase setPhotoAsPrimaryClinicPhotoUseCase;
    @Inject
    GetClinicPhotoByIdUseCase getClinicPhotoByIdUseCase;
    @Inject
    GetEquipmentsByClinicIdUseCase getEquipmentsByClinicIdUseCase;
    @Inject
    GetAvailabilitiesByClinicIdUseCase getAvailabilitiesByClinicIdUseCase;
    @Inject
    GetClinicsByLandlordIdUseCase getClinicsByLandlordIdUseCase;
    @Inject
    GetFilteredClinicsUseCase getFilteredClinicsUseCase;

    @Inject
    RequestContext requestContext;

    @POST
    @Transactional
    @LandlordOnly
    public Response createClinic(@Valid CreateClinicDTO clinicRequest) {
        try {
            User loggedInUser = requestContext.getUser();
            Clinic createdClinic = createClinicUseCase.execute(clinicRequest.toClinic());
            createdClinic =
                    assignClinicToUserUseCase.execute(createdClinic.getId(), loggedInUser.getId());

            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("Clinic Created", createdClinic)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @UserOnly
    public Response getFilteredClinics(
            @QueryParam("photos") @DefaultValue("false") boolean includePhotos,
            @QueryParam("equipments") @DefaultValue("false") boolean includeEquipments,
            @QueryParam("availabilities") @DefaultValue("false") boolean includeAvailabilities,
            @QueryParam("date") String targetDate) {
        try {
            ClinicQueryFilterDTO queryFilterDTO = new ClinicQueryFilterDTO(includePhotos,
                    includeEquipments, includeAvailabilities);

            if (targetDate != null) {
                Date formattedDate = Date.valueOf(targetDate);
                queryFilterDTO.setTargetDate(formattedDate);
            }

            List<GetClinicDTO> clinics = getFilteredClinicsUseCase.execute(queryFilterDTO);
            return Response.ok(ResponseDTO.success("Clinics Fetched", clinics)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/landlord")
    @LandlordOnly
    public Response getAllClinicsByLandlord() {
        try {
            User loggedInUser = requestContext.getUser();
            List<Clinic> clinics = getClinicsByLandlordIdUseCase.execute(loggedInUser.getId());
            return Response.ok(ResponseDTO.success("Clinics Fetched", clinics)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    @UserOnly
    public Response getClinicById(@PathParam("id") Long id,
            @QueryParam("photos") @DefaultValue("false") boolean includePhotos,
            @QueryParam("equipments") @DefaultValue("false") boolean includeEquipments,
            @QueryParam("availabilities") @DefaultValue("false") boolean includeAvailabilities) {
        try {
            ClinicQueryFilterDTO queryFilterDTO = new ClinicQueryFilterDTO(includePhotos,
                    includeEquipments, includeAvailabilities);
            GetClinicDTO clinicResponse = getClinicByIdUseCase.execute(id, queryFilterDTO);
            return Response.ok(ResponseDTO.success("Clinic Fetched", clinicResponse)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @LandlordOnly
    @Transactional
    public Response deleteClinicById(@PathParam("id") Long id) {
        try {
            User loggedInUser = requestContext.getUser();
            deleteClinicByIdUseCase.execute(id, loggedInUser.getId());

            return Response.ok(ResponseDTO.success("Clinic Deleted")).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}/photos")
    @UserOnly
    public Response getPhotosByClinicId(@PathParam("id") Long id) {
        try {
            List<GetClinicPhotoDTO> clinicPhotos = getPhotosByClinicIdUseCase.execute(id);

            return Response.ok(ResponseDTO.success("ClinicPhoto Fetched", clinicPhotos)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{id}/primary-photo")
    @LandlordOnly
    @Transactional
    public Response setPrimaryPhoto(@PathParam("id") Long clinicId,
            @Valid SetPhotoAsPrimaryDTO request) {
        try {
            User loggedInUser = requestContext.getUser();
            setPhotoAsPrimaryClinicPhotoUseCase.execute(request.getPhotoId(), clinicId,
                    loggedInUser.getId());

            return Response.ok(ResponseDTO.success("Clinic Photo Updated")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}/equipments")
    @UserOnly
    public Response getEquipmentsByClinicId(@PathParam("id") Long id) {
        try {
            List<GetClinicEquipmentDTO> clinicEquipments =
                    getEquipmentsByClinicIdUseCase.execute(id);

            return Response.ok(ResponseDTO.success("ClinicEquipment Fetched", clinicEquipments))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}/availabilities")
    @UserOnly
    public Response getAvailabilitiesByClinicId(@PathParam("id") Long id) {
        try {
            List<GetClinicAvailabilityDTO> clinicAvailabilities =
                    getAvailabilitiesByClinicIdUseCase.execute(id);

            return Response
                    .ok(ResponseDTO.success("ClinicAvailability Fetched", clinicAvailabilities))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }
}
