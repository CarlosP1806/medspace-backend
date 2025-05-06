package com.medspace.domain.repository;

import java.util.List;
import com.medspace.domain.model.RentRequestDay;

public interface RentRequestDayRepository {
    public RentRequestDay insertRentRequestDay(RentRequestDay rentRequestDay, Long rentRequestId);

    public List<RentRequestDay> getRentRequestDaysByRentRequestId(Long rentRequestId);
}
