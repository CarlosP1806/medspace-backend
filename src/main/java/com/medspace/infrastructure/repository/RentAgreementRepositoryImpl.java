package infrastructure.repository;

import domain.model.RentAgreement;
import domain.repository.RentAgreementRepository;
import infrastructure.entity.RentAgreementEntity;
import infrastructure.mapper.RentAgreementMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentAgreementRepositoryImpl implements RentAgreementRepository, PanacheRepositoryBase<RentAgreementEntity, UUID> {

    @Override
    public void insert(RentAgreement agreement) {
        persist(RentAgreementMapper.toEntity(agreement));
    }

    @Override
    public List<RentAgreement> getAllRentAgreements() {
        return findAll().stream()
            .map(RentAgreementMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<RentAgreement> findDomainById(UUID id) {  // CAMBIADO
        return findByIdOptional(id).map(RentAgreementMapper::toDomain);
    }

    @Override
    public boolean deleteById(UUID id) {
        return PanacheRepositoryBase.super.deleteById(id);
    }
}
