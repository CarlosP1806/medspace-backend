package infrastructure.controller;

import application.service.RentAgreementService;
import infrastructure.dto.CreateRentAgreementDTO;
import infrastructure.dto.GetRentAgreementDTO;
import infrastructure.mapper.RentAgreementMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/rent-agreements")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RentAgreementController {

    @Inject
    RentAgreementService service;

    @GET
    public List<GetRentAgreementDTO> getAll() {
        return service.getAll().stream()
                .map(RentAgreementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public GetRentAgreementDTO getById(@PathParam("id") UUID id) {
        return service.getById(id)
                .map(RentAgreementMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("RentAgreement not found"));
    }

    @POST
    @Transactional
    public void create(CreateRentAgreementDTO dto) {
        service.create(dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") UUID id) {
        service.delete(id);
    }
}
