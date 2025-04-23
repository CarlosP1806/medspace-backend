package com.medspace.infrastructure.rest.filters;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.UserRepository;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.rest.context.RequestContext;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class FirebaseAuthFilter implements ContainerRequestFilter {

    // @Inject
    // private UserRepository userRepository;

    @Inject
    RequestContext requestContext;

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        String authHeader = ctx.getHeaderString("Authorization");

        // Check if the header is null or empty
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.setUserId(null);
            return;
        }



        // Extract the token from the header
        String token = authHeader.substring("Bearer".length()).trim();

        // Validate the token using Firebase Admin SDK and get the user
        try {

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            // User u = userRepository.getUserByFireBaseId(decodedToken.getUid());
            requestContext.setUserId(decodedToken.getUid());


        } catch (FirebaseAuthException e) {

            requestContext.setUserId(null);


        } catch (Exception e) {

            ctx.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error("Internal error adding user property to request"))
                    .build());


        }
    }

}


