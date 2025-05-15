package com.medspace.application.usecase.rent;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.RentRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CancelRentRequestUseCase {
    @Inject
    RentService rentService;

    public void execute(Long rentRequestId, Long loggedUserId) {
        Boolean isSender = rentService.validateRentRequestSentByTenant(rentRequestId, loggedUserId);
        if (!isSender) {
            throw new IllegalArgumentException("Cancel unauthorized");
        }
        Boolean isPending = rentService.isRentRequestPending(rentRequestId);
        if (!isPending) {
            throw new IllegalArgumentException("Rent request is already processed");
        }
        rentService.updateRentRequestStatus(rentRequestId, RentRequest.Status.CANCELED);
    }
}
