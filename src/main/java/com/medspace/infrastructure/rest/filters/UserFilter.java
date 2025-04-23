package com.medspace.infrastructure.rest.filters;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.rest.annotations.UserOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import java.io.IOException;


@Provider
@UserOnly
@Priority(Priorities.AUTHORIZATION)
public class UserFilter implements ContainerRequestFilter {

    @Inject
    RequestContext requestContext;

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        if (requestContext.getUserId() == null) {
            ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(ResponseDTO.error("User not authenticated")).build());
        }

    }


}
