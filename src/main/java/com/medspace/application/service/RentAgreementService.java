package application.service;

import domain.model.RentAgreement;
import domain.repository.RentAgreementRepository;
import infrastructure.dto.CreateRentAgreementDTO;
import infrastructure.mapper.RentAgreementMapper;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RentAgreementService {

    private final RentAgreementRepository repository;

    public RentAgreementService(RentAgreementRepository repository) {
        this.repository = repository;
    }

    public void create(CreateRentAgreementDTO dto) {
        RentAgreement agreement = RentAgreementMapper.fromDTO(dto);
        repository.insert(agreement);
    }

    public List<RentAgreement> getAll() {
        return repository.getAllRentAgreements(); // corregido
    }

    public Optional<RentAgreement> getById(UUID id) {
        return repository.findDomainById(id);  // CAMBIADO
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
