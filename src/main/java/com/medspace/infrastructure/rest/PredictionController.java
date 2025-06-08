package com.medspace.infrastructure.rest;


import com.medspace.domain.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medspace.application.usecase.rent.payment.*;
import com.medspace.domain.model.Payment;
import com.medspace.infrastructure.dto.ResponseDTO;
import com.medspace.infrastructure.rest.annotations.LandlordOnly;
import com.medspace.infrastructure.rest.annotations.UserOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@ApplicationScoped
@Path("/predictions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@UserOnly
public class PredictionController {

    @Inject
    GetAllPaymentsUseCase getAllPaymentsUseCase;

    @Inject
    RequestContext requestContext;


    @GET
    @LandlordOnly
    @Path("/earnings")
    public Response predictEarnings() {

        try {
            User loggedInUser = requestContext.getUser();


            List<Payment> payments = getAllPaymentsUseCase.execute(loggedInUser);

            if (payments == null || payments.isEmpty()) {
                return Response.ok(ResponseDTO.success("No payments", payments)).build();

            }

            // Convert payments to the desired format
            List<Map<String, Object>> paymentData = new ArrayList<>();

            for (Payment payment : payments) {
                Map<String, Object> paymentMap = new HashMap<>();
                paymentMap.put("amount", payment.getAmount());
                if (payment.getPaymentDate() != null) {
                    paymentMap.put("paymentDate",
                            payment.getPaymentDate().toString().substring(0, 10));
                } else {
                    // Use createdAt date if paymentDate is null
                    paymentMap.put("paymentDate",
                            payment.getCreatedAt().toString().substring(0, 10));
                }
                paymentData.add(paymentMap);
            }

            // Convert to JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String jsonBody = mapper.writeValueAsString(paymentData);

            // print the JSON body for debugging
            System.out.println("JSON Body: " + jsonBody);

            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();

            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create(
                            "https://python-backend-567161984682.us-central1.run.app/predict_earnings"))
                    .header("Content-Type", "application/json")
                    .POST(java.net.http.HttpRequest.BodyPublishers.ofString(jsonBody)).build();

            java.net.http.HttpResponse<String> response =
                    client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());


            // print response status code
            System.out.println("Response Status Code: " + response.statusCode());



            return Response.ok(ResponseDTO.success("Predicted payments", response.body())).build();



        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseDTO.error(e.getMessage())).build();
        }
    }


}
