package com.medspace.domain.repository;

import com.medspace.domain.model.RentRequest;
import com.medspace.infrastructure.dto.rentRequest.RentRequestQueryFilterDTO;
import java.util.List;

public interface RentRequestRepository {

    RentRequest insert(RentRequest rentRequest, Long tenantId, Long clinicId);

    List<RentRequest> findAllRequests();

    List<RentRequest> findByLandlordId(RentRequestQueryFilterDTO filterDTO);

    List<RentRequest> findByTenantId(RentRequestQueryFilterDTO filterDTO);

    RentRequest findRequestById(Long id);

    void updateRentRequestStatus(Long id, RentRequest.Status status);

    boolean deleteById(Long id);
}
