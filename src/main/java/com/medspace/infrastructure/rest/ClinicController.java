package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.clinic.*;
import java.util.Map;
import org.checkerframework.common.reflection.qual.GetClass;
import com.medspace.application.usecase.clinic.availability.GetAvailabilitiesByClinicIdUseCase;
import com.medspace.application.usecase.clinic.equipment.GetEquipmentsByClinicIdUseCase;
import com.medspace.application.usecase.clinic.photo.GetClinicPhotoByIdUseCase;
import com.medspace.application.usecase.clinic.photo.GetPhotosByClinicIdUseCase;
import com.medspace.application.usecase.clinic.photo.SetPhotoAsPrimaryClinicPhotoUseCase;
import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.*;
import com.medspace.infrastructure.dto.clinic.ClinicQueryDTO;
import com.medspace.infrastructure.dto.clinic.CityFilterDTO;
import com.medspace.infrastructure.dto.clinic.CreateClinicDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicAvailabilityDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicEquipmentDTO;
import com.medspace.infrastructure.dto.clinic.GetClinicPhotoDTO;
import com.medspace.infrastructure.dto.clinic.MyClinicDTO;
import com.medspace.infrastructure.dto.clinic.SetPhotoAsPrimaryDTO;
import com.medspace.infrastructure.dto.clinic.UpdateClinicDTO;
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
import java.text.Normalizer;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

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
    GetTotalClinicsCountUseCase getTotalClinicsCountUseCase;
    @Inject
    UpdateClinicUseCase updateClinicUseCase;
    @Inject
    GetClinicCountByCategoryUseCase getClinicCountByCategoryUseCase;
    @Inject
    GetClinicCountByCityUseCase getClinicCountByCityUseCase;

    @Inject
    GetAllCitiesWithClinicsUseCase getAllClinicCitiesUseCase;

    @Inject
    GetClinicsByCategoryAndCityUseCase getClinicsByCategoryAndCityUseCase;
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
            @QueryParam("date") String targetDate,
            @QueryParam("equipmentList") List<String> equipmentList,
            @QueryParam("hour") String targetHour, @QueryParam("city") String targetCity) {
        try {
            ClinicQueryDTO queryFilterDTO =
                    new ClinicQueryDTO(includePhotos, includeEquipments, includeAvailabilities);

            if (targetDate != null) {
                Date formattedDate = Date.valueOf(targetDate);
                queryFilterDTO.setTargetDate(formattedDate);
            }

            if (equipmentList != null && !equipmentList.isEmpty()) {
                queryFilterDTO.setEquipmentList(equipmentList);
            }

            if (targetHour != null) {
                queryFilterDTO.setTargetHour(LocalTime.parse(targetHour));
            }

            if (targetCity != null) {
                queryFilterDTO.setTargetCity(targetCity);
            }

            List<GetClinicDTO> clinics = getFilteredClinicsUseCase.execute(queryFilterDTO);
            return Response.ok(ResponseDTO.success("Clinics Fetched", clinics)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/my-clinics")
    @LandlordOnly
    public Response getAllClinicsByLandlord() {
        try {
            User loggedInUser = requestContext.getUser();

            List<MyClinicDTO> clinics = getClinicsByLandlordIdUseCase.execute(loggedInUser.getId());

            return Response.ok(ResponseDTO.success("Clinics Fetched", clinics)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/clinics-count")

    public Response getTotalClinicsCount() {
        try {
            long total = getTotalClinicsCountUseCase.execute();
            return Response.ok(ResponseDTO.success("Total clinics count fetched", total)).build();
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
            ClinicQueryDTO queryFilterDTO =
                    new ClinicQueryDTO(includePhotos, includeEquipments, includeAvailabilities);
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

    @PUT
    @Path("/{id}")
    @LandlordOnly
    public Response updateClinic(@PathParam("id") Long id, @Valid UpdateClinicDTO request) {

        User loggedInUser = requestContext.getUser();
        updateClinicUseCase.execute(id, request.toClinic(), loggedInUser.getId());

        return Response.ok(ResponseDTO.success("Clinic updated successfully")).build();
    }

    @GET
    @Path("/{category}/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClinicCountByCategory(@PathParam("category") String category) {
        try {

            Clinic.Category enumCategory = Clinic.Category.valueOf(category.toUpperCase());
            long count = getClinicCountByCategoryUseCase.execute(enumCategory);

            Map<String, Object> response = new HashMap<>();
            response.put("category", enumCategory.name());
            response.put("count", count);

            return Response.ok(response).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Invalid category: " + category)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", e.getMessage())).build();
        }
    }
 @GET
@Path("/city-clinics/{city}")
@Produces(MediaType.APPLICATION_JSON)
public Response getClinicCountByCity(@PathParam("city") String city) {
    try {
        long count = getClinicCountByCityUseCase.execute(city);
        return Response.ok(ResponseDTO.success("Clinic count fetched", Map.of("city", city, "count", count))).build();
    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ResponseDTO.error("Error counting clinics by city")).build();
    }
}

@GET
@Path("/cities")
@Produces(MediaType.APPLICATION_JSON)
public Response getAllCitiesWithClinics() {
    try {
        Set<String> rawCities = getAllClinicCitiesUseCase.execute();

        List<CityFilterDTO> cities = rawCities.stream()
            .map(city -> new CityFilterDTO(
                city, // label
                normalizeCity(city) 
            ))
            .collect(Collectors.toList());

        return Response.ok(ResponseDTO.success("Cities fetched", cities)).build();
    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ResponseDTO.error("Error retrieving cities")).build();
    }
}




@GET
@Path("/filter")
@Produces(MediaType.APPLICATION_JSON)
public Response getClinicCountByCategoryAndCity(
    @QueryParam("category") String categoryRaw,
    @QueryParam("city") String cityRaw
) {
    try {
        if (categoryRaw == null || cityRaw == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ResponseDTO.error("Category and city are required"))
                    .build();
        }

        Clinic.Category category;
        try {
            category = Clinic.Category.valueOf(categoryRaw.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ResponseDTO.error("Invalid category value"))
                    .build();
        }

        String normalizedCity = normalizeCity(cityRaw);

        long count = getClinicsByCategoryAndCityUseCase.execute(category, normalizedCity);

        return Response.ok(ResponseDTO.success("Clinic count retrieved", count)).build();
    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ResponseDTO.error("Error retrieving clinic count")).build();
    }
}

private String normalizeCity(String city) {
    if (city == null) return null;
    return city.trim()
               .toLowerCase()
               .replaceAll("[áÁ]", "a")
               .replaceAll("[éÉ]", "e")
               .replaceAll("[íÍ]", "i")
               .replaceAll("[óÓ]", "o")
               .replaceAll("[úÚ]", "u")
               .replaceAll("\\s+", "_");
}

    }



