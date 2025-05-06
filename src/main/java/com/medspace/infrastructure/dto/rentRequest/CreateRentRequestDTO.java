package com.medspace.infrastructure.dto.rentRequest;

import com.google.firebase.database.annotations.NotNull;
import com.medspace.domain.model.RentRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentRequestDTO {

    @NotNull
    private Long tenantId;

    @NotNull
    private Long clinicId;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotBlank
    private String comments;

    @NotBlank
    private String status;

    @NotEmpty
    private List<Date> dates;

    public RentRequest toModel() {
        RentRequest rr = new RentRequest();
        rr.setStartDate(startDate);
        rr.setEndDate(endDate);
        rr.setComments(comments);
        rr.setStatus(status);
        return rr;
    }
}
