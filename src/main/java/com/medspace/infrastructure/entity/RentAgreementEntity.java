package infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rent_agreement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentAgreementEntity {

    @Id
    private UUID id;

    @Column(name = "landlord_id", nullable = false)
    private UUID landlordId;

    @Column(name = "clinic_id", nullable = false)
    private UUID clinicId;

    @Column(name = "rent_request_id", nullable = false)
    private UUID rentRequestId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private BigDecimal price;
}
