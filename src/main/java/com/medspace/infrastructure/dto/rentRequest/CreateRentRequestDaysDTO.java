package com.medspace.infrastructure.dto.rentRequest;

import java.sql.Date;
import java.util.List;
import com.medspace.domain.model.RentRequestDay;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentRequestDaysDTO {
    @NotEmpty
    private List<Date> dates;

    public List<RentRequestDay> toRentRequestDays() {
        return dates.stream().map(date -> {
            RentRequestDay rentRequestDay = new RentRequestDay();
            rentRequestDay.setDate(date);
            return rentRequestDay;
        }).toList();
    }
}
