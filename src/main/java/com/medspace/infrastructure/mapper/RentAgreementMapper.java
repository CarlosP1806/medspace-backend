package infrastructure.mapper;

import domain.model.RentAgreement;
import infrastructure.dto.CreateRentAgreementDTO;
import infrastructure.dto.GetRentAgreementDTO;
import infrastructure.entity.RentAgreementEntity;

import java.util.UUID;

public class RentAgreementMapper {

    public static RentAgreementEntity toEntity(RentAgreement agreement) {
        RentAgreementEntity entity = new RentAgreementEntity();
        entity.setId(agreement.getId());
        entity.setLandlordId(agreement.getLandlordId());
        entity.setClinicId(agreement.getClinicId());
        entity.setRentRequestId(agreement.getRentRequestId());
        entity.setStartDate(agreement.getStartDate());
        entity.setEndDate(agreement.getEndDate());
        entity.setPrice(agreement.getPrice());
        return entity;
    }

    public static RentAgreement toDomain(RentAgreementEntity entity) {
        return new RentAgreement(
            entity.getId(),
            entity.getLandlordId(),
            entity.getClinicId(),
            entity.getRentRequestId(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getPrice()
        );
    }

    public static RentAgreement fromDTO(CreateRentAgreementDTO dto) {
        return new RentAgreement(
            UUID.randomUUID(),
            dto.landlordId,
            dto.clinicId,
            dto.rentRequestId,
            dto.startDate,
            dto.endDate,
            dto.price
        );
    }

    public static GetRentAgreementDTO toDTO(RentAgreement agreement) {
        GetRentAgreementDTO dto = new GetRentAgreementDTO();
        dto.id = agreement.getId();
        dto.landlordId = agreement.getLandlordId();
        dto.clinicId = agreement.getClinicId();
        dto.rentRequestId = agreement.getRentRequestId();
        dto.startDate = agreement.getStartDate();
        dto.endDate = agreement.getEndDate();
        dto.price = agreement.getPrice();
        return dto;
    }
}
