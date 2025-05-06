package com.medspace.infrastructure.dto.rentRequest;

import java.sql.Date;
import com.medspace.domain.model.RentRequestDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRentRequestDayDTO {
    private Long id;
    private Long rentRequestId;
    private Date date;

    public GetRentRequestDayDTO(RentRequestDay rentRequestDay) {
        this.id = rentRequestDay.getId();
        this.rentRequestId = rentRequestDay.getRentRequest().getId();
        this.date = rentRequestDay.getDate();
    }
}
