package domain.repository;

import domain.model.RentAgreement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentAgreementRepository {
    void insert(RentAgreement agreement);
    List<RentAgreement> getAllRentAgreements();
    Optional<RentAgreement> findDomainById(UUID id);  // CAMBIADO
    boolean deleteById(UUID id);
}
