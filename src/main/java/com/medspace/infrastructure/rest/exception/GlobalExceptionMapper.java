package com.medspace.infrastructure.rest.exception;

import com.medspace.infrastructure.dto.ResponseDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

// Import all HTTP exceptions
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.RedirectionException;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.ServiceUnavailableException;
import jakarta.ws.rs.WebApplicationException;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        int statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(); // 500 Internal
                                                                                // Server Error
                                                                                // (default)

        if (exception instanceof BadRequestException) {
            statusCode = Response.Status.BAD_REQUEST.getStatusCode(); // 400 Bad Request
        } else if (exception instanceof NotAuthorizedException) {
            statusCode = Response.Status.UNAUTHORIZED.getStatusCode(); // 401 Unauthorized
        } else if (exception instanceof ForbiddenException) {
            statusCode = Response.Status.FORBIDDEN.getStatusCode(); // 403 Forbidden
        } else if (exception instanceof NotFoundException) {
            statusCode = Response.Status.NOT_FOUND.getStatusCode(); // 404 Not Found
        } else if (exception instanceof NotAllowedException) {
            statusCode = Response.Status.METHOD_NOT_ALLOWED.getStatusCode(); // 405 Method Not
                                                                             // Allowed
        } else if (exception instanceof NotSupportedException) {
            statusCode = Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(); // 415 Unsupported
                                                                                 // Media Type
        } else if (exception instanceof InternalServerErrorException) {
            statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(); // 500 Internal
                                                                                // Server Error
        } else if (exception instanceof ServiceUnavailableException) {
            statusCode = Response.Status.SERVICE_UNAVAILABLE.getStatusCode(); // 503 Service
                                                                              // Unavailable
        } else if (exception instanceof RedirectionException) {
            statusCode = ((RedirectionException) exception).getResponse().getStatus(); // 3xx
                                                                                       // Redirection
        } else if (exception instanceof ClientErrorException) {
            statusCode = ((ClientErrorException) exception).getResponse().getStatus(); // 4xx Client
                                                                                       // Error
        } else if (exception instanceof ServerErrorException) {
            statusCode = ((ServerErrorException) exception).getResponse().getStatus(); // 5xx Server
                                                                                       // Error
        } else if (exception instanceof WebApplicationException) {
            statusCode = ((WebApplicationException) exception).getResponse().getStatus(); // General
                                                                                          // HTTP
                                                                                          // Exception
        }

        return Response.status(statusCode).entity(ResponseDTO.error(exception.getMessage()))
                .build();
    }
}
