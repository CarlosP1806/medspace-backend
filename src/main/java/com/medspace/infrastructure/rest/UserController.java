package com.medspace.infrastructure.rest;

import java.util.List;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import com.medspace.application.usecase.tenantSpecialties.GetTenantSpecialtyByIdUseCase;
import com.medspace.application.usecase.user.CreateUserUseCase;
import com.medspace.application.usecase.user.DeleteUserByIdUseCase;
import com.medspace.application.usecase.user.GetAllUsersUseCase;
import com.medspace.application.usecase.user.GetUserByIdUseCase;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.CreateUserDTO;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.rest.annotations.UserOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    RequestContext requestContext;


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createUser(@MultipartForm @Valid CreateUserDTO userRequest) {
        try {
            if (getTenantSpecialtyByIdUseCase.execute(userRequest.getTenantSpecialtyId()) == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(ResponseDTO.error("Tenant specialty with id "
                                + userRequest.getTenantSpecialtyId() + " not found"))
                        .build();

            }


            createUserUseCase.execute(userRequest.toUser(), userRequest.getProfilePhoto(),
                    userRequest.getTenantProfessionalLicense());


            return Response.ok(ResponseDTO.success("User Created")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage())
                    .build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        try {
            User user = getUserByIdUseCase.execute(id);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error("User not found")).build();
            }
            deleteUserByIdUseCase.execute(id);

            return Response.ok(ResponseDTO.success("User Deleted")).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {
        try {
            User user = getUserByIdUseCase.execute(id);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error("User not found")).build();
            }
            return Response.ok(ResponseDTO.success("User Fetched", user)).build();
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


            String userId = requestContext.getUserId();


            // if (userId == null) {
            // return Response.status(Response.Status.UNAUTHORIZED)
            // .entity(ResponseDTO.error("User not authenticated")).build();
            // }

            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(ResponseDTO.success("Userid", userId)).build();



        } catch (Exception e) {
            // TODO: handle exception
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();

        }

    }



}
