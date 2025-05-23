package com.medspace.infrastructure.rest;

import java.util.List;
import com.medspace.application.usecase.rent.review.CreateReviewUseCase;
import com.medspace.application.usecase.rent.review.GetReviewsByClinicIdUseCase;
import com.medspace.application.usecase.rent.review.GetReviewsByUserIdUseCase;
import com.medspace.domain.model.Review;
import com.medspace.infrastructure.rest.context.RequestContext;
import com.medspace.infrastructure.rest.annotations.LandlordOnly;
import com.medspace.infrastructure.rest.annotations.TenantOnly;
import com.medspace.infrastructure.rest.annotations.UserOnly;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.review.CreateReviewDTO;
import com.medspace.infrastructure.dto.review.GetReviewDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/reviews")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ReviewController {
    @Inject
    CreateReviewUseCase createReviewUseCase;

    @Inject
    GetReviewsByUserIdUseCase getReviewsByUserIdUseCase;

    @Inject
    GetReviewsByClinicIdUseCase getReviewsByClinicIdUseCase;

    @Inject
    RequestContext requestContext;

    @GET
    @UserOnly
    @Path("/user/{id}")
    public Response getReviewsByUserId(@PathParam("id") Long id) {
        List<GetReviewDTO> reviews = getReviewsByUserIdUseCase.execute(id);

        return Response.ok(ResponseDTO.success("Reviews Fetched", reviews)).build();
    }

    @GET
    @UserOnly
    @Path("/clinic/{id}")
    public Response getReviewsByClinicId(@PathParam("id") Long id) {
        List<GetReviewDTO> reviews = getReviewsByClinicIdUseCase.execute(id);

        return Response.ok(ResponseDTO.success("Reviews Fetched", reviews)).build();
    }

    @POST
    @LandlordOnly
    @Path("/tenant")
    public Response createTenantReview(@Valid CreateReviewDTO request) {
        createReviewUseCase.execute(request, Review.Type.TENANT);

        return Response.ok(ResponseDTO.success("Review Created")).build();
    }

    @POST
    @TenantOnly
    @Path("/landlord")
    public Response createLandlordReview(@Valid CreateReviewDTO request) {
        createReviewUseCase.execute(request, Review.Type.LANDLORD);

        return Response.ok(ResponseDTO.success("Review Created")).build();
    }

    @POST
    @TenantOnly
    @Path("/clinic")
    public Response createClinicReview(@Valid CreateReviewDTO request) {
        createReviewUseCase.execute(request, Review.Type.CLINIC);

        return Response.ok(ResponseDTO.success("Review Created")).build();
    }



}
