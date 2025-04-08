package com.medspace.infrastructure.rest;

import com.medspace.application.usecase.CreateProductUseCase;
import com.medspace.infrastructure.dto.CreateProductDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/products")
@Consumes("application/json")
@Produces("application/json")
public class ProductController {

    @Inject
    CreateProductUseCase createProductUseCase;

    @POST
    public Response createProduct(CreateProductDTO productRequest) {
        try {
            return Response.ok(createProductUseCase.execute(productRequest.toProduct())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }
}
