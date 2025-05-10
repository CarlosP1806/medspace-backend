package domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class RentAgreement {
    private UUID id;
    private UUID landlordId;
    private UUID clinicId;
    private UUID rentRequestId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;

}
 