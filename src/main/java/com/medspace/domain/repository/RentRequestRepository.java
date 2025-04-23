package com.medspace.domain.repository;

import com.medspace.domain.model.RentRequest;
import java.util.List;

public interface RentRequestRepository {

    RentRequest insert(RentRequest rentRequest);

    List<RentRequest> findAllRequests();

    RentRequest findRequestById(Long id);

    boolean deleteById(Long id);
}
