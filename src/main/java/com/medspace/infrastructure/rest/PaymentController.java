package com.medspace.infrastructure.rest;

import com.medspace.domain.model.User;
import com.medspace.application.usecase.rent.payment.*;
import com.medspace.domain.model.Payment;
import com.medspace.infrastructure.dto.*;
import com.medspace.infrastructure.dto.payment.CreatePaymentDTO;
import com.medspace.infrastructure.dto.payment.PaymentResponseDTO;
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
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@UserOnly
public class PaymentController {
    @Inject
    CreatePaymentUseCase createPaymentUseCase;
    @Inject
    GetPaymentByIdUseCase getPaymentByIdUseCase;
    @Inject
    GetPaymentsByRentAgreementIdUseCase getPaymentsByRentAgreementIdUseCase;
    @Inject
    RequestContext requestContext;

    @POST
    @Transactional
    public Response createPayment(@Valid CreatePaymentDTO dto) {
        try {
            User user = requestContext.getUser();
            Payment payment = createPaymentUseCase.execute(dto.toPayment(), user);
            return Response.status(Response.Status.CREATED)
                    .entity(ResponseDTO.success("Payment Created", new PaymentResponseDTO(payment)))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error("An error occurred while creating the payment"))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPaymentById(@PathParam("id") Long id) {
        try {
            User user = requestContext.getUser();
            Payment payment = getPaymentByIdUseCase.execute(id, user);
            return Response
                    .ok(ResponseDTO.success("Payment Fetched", new PaymentResponseDTO(payment)))
                    .build();
        } catch (Exception e) {
            if (e.getMessage().contains("not found")) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error("Payment not found")).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error("An error occurred while fetching the payment"))
                    .build();
        }
    }

    @GET
    @Path("/rent-agreement/{rentAgreementId}")
    public Response getPaymentsByRentAgreementId(
            @PathParam("rentAgreementId") Long rentAgreementId) {
        try {
            User user = requestContext.getUser();
            List<Payment> payments =
                    getPaymentsByRentAgreementIdUseCase.execute(rentAgreementId, user);
            List<PaymentResponseDTO> dtos =
                    payments.stream().map(PaymentResponseDTO::new).collect(Collectors.toList());
            return Response.ok(ResponseDTO.success("Payments Fetched", dtos)).build();
        } catch (Exception e) {
            if (e.getMessage().contains("not found")) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(ResponseDTO.error("Rent agreement not found")).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error("An error occurred while fetching the payments"))
                    .build();
        }
    }
}
