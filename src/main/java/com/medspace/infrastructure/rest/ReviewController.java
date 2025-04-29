package com.medspace.infrastructure.rest;

import java.util.List;
import com.medspace.application.usecase.review.AssignReviewToAuthorUseCase;
import com.medspace.application.usecase.review.CreateReviewUseCase;
import com.medspace.application.usecase.review.DeleteReviewByIdUseCase;
import com.medspace.application.usecase.review.GetAllReviewsUseCase;
import com.medspace.application.usecase.review.GetReviewByIdUseCase;
import com.medspace.domain.model.Review;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.dto.review.CreateReviewDTO;
import com.medspace.infrastructure.dto.review.ReviewResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    GetAllReviewsUseCase getAllReviewsUseCase;
    @Inject
    GetReviewByIdUseCase getReviewByIdUseCase;
    @Inject
    DeleteReviewByIdUseCase deleteReviewByIdUseCase;
    @Inject
    AssignReviewToAuthorUseCase assignReviewToUserUseCase;

    @GET
    public Response getAllReviews() {
        try {
            List<Review> reviews = getAllReviewsUseCase.execute();
            List<ReviewResponseDTO> response =
                    reviews.stream().map(ReviewResponseDTO::fromReview).toList();
            return Response.ok(ResponseDTO.success("Reviews Fetched", response)).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }



    @POST
    @Transactional
    public Response createReview(@Valid CreateReviewDTO createReviewDTO) {
        try {
            Review review = createReviewDTO.toReview();
            Review createdReview = createReviewUseCase.execute(review);
            createdReview = assignReviewToUserUseCase.execute(createdReview.getId(),
                    createReviewDTO.getAuthorId());
            ReviewResponseDTO responseDTO = ReviewResponseDTO.fromReview(createdReview);
            return Response.ok(ResponseDTO.success("Review created", responseDTO)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getReviewById(@PathParam("id") Long id) {
        try {
            Review review = getReviewByIdUseCase.execute(id);
            if (review == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error("Clinic not Found")).build();
            }
            ReviewResponseDTO responseDTO = ReviewResponseDTO.fromReview(review);
            return Response.ok(ResponseDTO.success("Clinic Fetched", responseDTO)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReviewByIdUseCase(@PathParam("id") Long id) {
        try {
            deleteReviewByIdUseCase.execute(id);
            return Response.ok(ResponseDTO.success("Review Deleted")).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }
}
