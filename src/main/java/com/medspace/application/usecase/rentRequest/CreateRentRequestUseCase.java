package com.medspace.application.usecase.rentRequest;

import com.medspace.domain.model.RentRequest;

public interface CreateRentRequestUseCase {
    RentRequest execute(RentRequest rentRequest);
}
