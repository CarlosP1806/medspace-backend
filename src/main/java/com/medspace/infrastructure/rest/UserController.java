package com.medspace.infrastructure.rest;

import java.util.List;
import com.medspace.application.usecase.user.CreateUserUseCase;
import com.medspace.application.usecase.user.DeleteUserByIdUseCase;
import com.medspace.application.usecase.user.EditUserProfileByIdUseCase;
import com.medspace.application.usecase.user.GetAllUsersUseCase;
import com.medspace.application.usecase.user.GetUserByIdUseCase;
import com.medspace.application.usecase.user.GetUserPublicProfileUseCase;
import com.medspace.application.usecase.user.GetTenantCountUseCase;
import com.medspace.application.usecase.user.tenantSpecialties.GetTenantSpecialtyByIdUseCase;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.user.CreateUserDTO;
import com.medspace.infrastructure.dto.user.EditUserDTO;
import com.medspace.infrastructure.dto.user.GetUserPublicProfileDTO;
import com.medspace.infrastructure.rest.annotations.AnalystOnly;
import com.medspace.infrastructure.rest.annotations.UserOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/users")
@Consumes("application/json")
@Produces("application/json")
public class UserController {
    @Inject
    CreateUserUseCase createUserUseCase;
    @Inject
    GetTenantSpecialtyByIdUseCase getTenantSpecialtyByIdUseCase;
    @Inject
    GetUserByIdUseCase getUserByIdUseCase;
    @Inject
    GetAllUsersUseCase getAllUsersUseCase;
    @Inject
    DeleteUserByIdUseCase deleteUserByIdUseCase;
    @Inject
    GetUserPublicProfileUseCase getUserPublicProfileUseCase;
    @Inject
    GetTenantCountUseCase getTenantCountUseCase;

    @Inject
    RequestContext requestContext;

    @Inject
    EditUserProfileByIdUseCase editUserProfileByIdUseCase;


    @POST
    @Consumes("application/json")
    public Response createUser(@Valid CreateUserDTO userRequest) {
        try {
            String firebaseUid = requestContext.getFirebaseUid();

            if (firebaseUid == null) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(ResponseDTO.error("You need firebase UID to create account"))
                        .build();
            }

            if (userRequest.getUserType().equals("TENANT")) {

                if (userRequest.getTenantSpecialtyId() == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(ResponseDTO.error("Tenant specialty id is required for tenant"))
                            .build();
                }

                if (userRequest.getTenantLicensePath() == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(ResponseDTO.error(
                                    "Tenant TenantProfessionalLicense is required for tenant"))
                            .build();
                }

                if (getTenantSpecialtyByIdUseCase
                        .execute(userRequest.getTenantSpecialtyId()) == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(ResponseDTO.error("Tenant specialty with id "
                                    + userRequest.getTenantSpecialtyId() + " not found"))
                            .build();

                }
            }

            User user = userRequest.toUser();
            user.setFirebaseUid(firebaseUid);

            createUserUseCase.execute(user);

            return Response.ok(ResponseDTO.success("User Created")).build();
        } catch (Exception e) {
            if (e.getMessage().contains("User already exists")) {
                return Response.status(Response.Status.CONFLICT)
                        .entity(ResponseDTO.error(e.getMessage())).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        try {
            User user = getUserByIdUseCase.execute(id);

            return Response.ok(ResponseDTO.success("User Fetched", user)).build();
        } catch (Exception e) {

            if (e.getMessage().contains("not found")) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error(e.getMessage())).build();
            }

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{id}/public-profile")
    @UserOnly
    public Response getUserPublicProfile(@PathParam("id") Long id) {
        try {
            GetUserPublicProfileDTO userProfileDTO = getUserPublicProfileUseCase.execute(id);

            return Response.ok(ResponseDTO.success("User Fetched", userProfileDTO)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @GET
    public Response getAllUsers() {
        try {
            List<User> users = getAllUsersUseCase.execute();

            return Response.ok(ResponseDTO.success("Users Fetched", users)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }


    @GET
    @UserOnly
    @Path("/me")
    public Response getMyProfile() {
        try {
            User user = requestContext.getUser();

            return Response.ok(ResponseDTO.success("Users Fetched", user)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }

    }

    @PUT
    @UserOnly
    @Path("/me")
    public Response editMyProfile(@Valid EditUserDTO userRequest) {
        try {
            User user = requestContext.getUser();


            if (user.getUserType() == User.UserType.TENANT) {

                if (userRequest.getTenantSpecialtyId() != null && getTenantSpecialtyByIdUseCase
                        .execute(userRequest.getTenantSpecialtyId()) == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(ResponseDTO.error("Tenant specialty with id "
                                    + userRequest.getTenantSpecialtyId() + " not found"))
                            .build();

                }
            }

            editUserProfileByIdUseCase.execute(user.getId(), userRequest);

            return Response.ok(ResponseDTO.success("Users Updated")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }

    }


    @DELETE
    @UserOnly
    @Path("/me")
    public Response deleteUser() {
        try {
            User user = requestContext.getUser();

            deleteUserByIdUseCase.execute(user.getId());

            return Response.ok(ResponseDTO.success("User Deleted")).build();

        } catch (Exception e) {

            if (e.getMessage().contains("not found")) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error(e.getMessage())).build();
            }

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }
    @GET
    
    @Path("/specialist-count")
    @AnalystOnly
    public Response getTenantCount() {
        try {
            long totalTenants = getTenantCountUseCase.execute();
            return Response.ok(
                ResponseDTO.success("Total specialist count fetched", totalTenants)
            ).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(ResponseDTO.error(e.getMessage()))
                           .build();
        }
    }

}
