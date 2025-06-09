package com.medspace.infrastructure.rest;

import com.medspace.application.service.EarningsService;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.dto.common.WeeklyEarningsDTO;
import com.medspace.infrastructure.rest.annotations.LandlordOnly;
import com.medspace.infrastructure.rest.context.RequestContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/landlords/earnings/weekly")
@Produces(MediaType.APPLICATION_JSON)
@LandlordOnly
public class EarningsController {
    @Inject
    EarningsService earningsService;
    @Inject
    RequestContext requestContext;

    @GET
    public List<WeeklyEarningsDTO> getWeeklyEarnings() {
        User landlord = requestContext.getUser();
        return earningsService.getLast4WeeksEarningsForLandlord(landlord.getId());
    }
}
