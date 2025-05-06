package com.medspace.infrastructure.dto.rentRequest;

import com.medspace.domain.model.RentRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private Long clinicId;

    @NotBlank
    private String comments;

    @NotBlank
    private String status;

    @NotEmpty
    private List<Date> dates;

    public RentRequest toModel() {
        RentRequest rr = new RentRequest();
        rr.setComments(comments);
        rr.setStatus(status);
        return rr;
    }
}
