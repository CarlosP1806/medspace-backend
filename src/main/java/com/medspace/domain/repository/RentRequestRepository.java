package com.medspace.domain.repository;

import com.medspace.domain.model.RentRequest;
import java.util.List;

public interface RentRequestRepository {

    RentRequest insert(RentRequest rentRequest, Long tenantId, Long clinicId);

    List<RentRequest> findAllRequests();

    RentRequest findRequestById(Long id);

    boolean deleteById(Long id);
}
