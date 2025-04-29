package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.notification.*;
import com.medspace.domain.model.Notification;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.*;
import com.medspace.infrastructure.rest.annotations.UserOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@UserOnly
public class NotificationController {

    @Inject
    GetUserNotificationsUseCase getUserNotificationsUseCase;

    @Inject
    MarkNotificationAsReadUseCase markNotificationAsReadUseCase;

    @Inject
    RequestContext requestContext;

    @GET
    public Response getNotifications() {
        try {
            User loggedUser = requestContext.getUser();
            List<Notification> notifications = getUserNotificationsUseCase.execute(loggedUser.getId());
            return Response.ok(ResponseDTO.success("Notifications fetched", notifications)).build();
        } catch (Exception e) {
            // Log the exception details for internal debugging
            Logger.getLogger(NotificationController.class.getName()).severe("Error fetching notifications: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ResponseDTO.error("An unexpected error occurred. Please try again later."))
                .build();
        }
    }

    @PUT
    @Path("/{id}/read")
    @Transactional
    public Response markAsRead(@PathParam("id") Long id) {
        try {
            User loggedUser = requestContext.getUser();
            markNotificationAsReadUseCase.execute(id, loggedUser.getId());
            return Response.ok(ResponseDTO.success("Notification marked as read")).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(ResponseDTO.error(e.getMessage()))
                .build();
        } catch (ForbiddenException e) {
            return Response.status(Response.Status.FORBIDDEN)
                .entity(ResponseDTO.error(e.getMessage()))
                .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(ResponseDTO.error(e.getMessage()))
                .build();
        }
    }
} 