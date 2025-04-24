package com.medspace.infrastructure.rest.filters;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.rest.annotations.AnalystOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import java.io.IOException;


@Provider
@AnalystOnly
@Priority(Priorities.AUTHORIZATION)
public class AnalystFilter implements ContainerRequestFilter {

    @Inject
    RequestContext requestContext;

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        if (requestContext.getUser() == null) {
            ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(ResponseDTO.error("User not authenticated")).build());
        }


        if (requestContext.getUser().getUserType() != User.UserType.ANALYST) {
            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(ResponseDTO.error("You must be a analyst to access this route"))
                    .build());
        }

    }

}
