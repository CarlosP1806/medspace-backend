package com.medspace.application.usecase.rentRequest;

import com.medspace.domain.model.RentRequest;
import java.util.List;

public interface ListRentRequestUseCase {
    List<RentRequest> execute();
}
