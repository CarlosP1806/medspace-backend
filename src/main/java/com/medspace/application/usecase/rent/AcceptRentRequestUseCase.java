package com.medspace.application.usecase.rent;

import com.medspace.application.service.RentService;
import com.medspace.domain.model.RentRequest;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AcceptRentRequestUseCase {
    @Inject
    RentService rentService;

    public Boolean execute(Long rentRequestId, Long loggedUserId) {
        Boolean isOwner = rentService.validateRentRequestOwnership(rentRequestId, loggedUserId);
        if (!isOwner) {
            throw new ForbiddenException("Accept unauthorized");
        }

        Boolean isPaymentSuccessful = rentService.processRentRequestPayment(rentRequestId);
        RentRequest.Status status =
                isPaymentSuccessful ? RentRequest.Status.ACCEPTED : RentRequest.Status.REJECTED;

        rentService.updateRentRequestStatus(rentRequestId, status);
        return isPaymentSuccessful;
    }
}
