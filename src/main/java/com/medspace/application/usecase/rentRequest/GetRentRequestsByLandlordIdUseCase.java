package com.medspace.application.usecase.rentRequest;

import java.util.List;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.RentRequestRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetRentRequestsByLandlordIdUseCase {
    @Inject
    RentRequestRepository rentRequestRepository;

    public List<RentRequest> execute(Long landlordId) {
        return rentRequestRepository.findByLandlordId(landlordId);
    }

}
