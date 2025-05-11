package com.medspace.application.usecase.rent;

import com.medspace.domain.model.RentRequest;
import com.medspace.application.service.RentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RejectRentRequestUseCase {
    @Inject
    RentService rentService;

    public void execute(Long rentRequestId, Long loggedUserId) {
        Boolean isOnwer = rentService.validateRentRequestOwnership(rentRequestId, loggedUserId);
        if (!isOnwer) {
            throw new IllegalArgumentException("Reject unauthorized");
        }
        Boolean isPending = rentService.isRentRequestPending(rentRequestId);
        if (!isPending) {
            throw new IllegalArgumentException("Rent request is already processed");
        }

        rentService.updateRentRequestStatus(rentRequestId, RentRequest.Status.REJECTED);
    }

}
