package infrastructure.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreateRentAgreementDTO {
    public UUID landlordId;
    public UUID clinicId;
    public UUID rentRequestId;
    public LocalDate startDate;
    public LocalDate endDate;
    public BigDecimal price;
}
