package com.medspace.application.service;

import com.medspace.domain.model.Clinic;
import com.medspace.domain.model.RentRequest;
import com.medspace.domain.repository.ClinicRepository;
import com.medspace.domain.repository.RentRequestRepository;
import com.medspace.infrastructure.dto.common.WeeklyEarningsDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class EarningsService {
    @Inject
    ClinicRepository clinicRepository;
    @Inject
    RentRequestRepository rentRequestRepository;

    public List<WeeklyEarningsDTO> getLast4WeeksEarningsForLandlord(Long landlordId) {
        List<Clinic> clinics = clinicRepository.getClinicsByLandlordId(landlordId);
        if (clinics.isEmpty())
            return Collections.emptyList();
        Set<Long> clinicIds = clinics.stream().map(Clinic::getId).collect(Collectors.toSet());
        Map<Long, Clinic> clinicMap =
                clinics.stream().collect(Collectors.toMap(Clinic::getId, c -> c));

        List<RentRequest> allRequests = rentRequestRepository.findAllRequests();
        List<RentRequest> acceptedRequests = allRequests.stream()
                .filter(r -> r.getStatus() == RentRequest.Status.ACCEPTED)
                .filter(r -> r.getClinic() != null && clinicIds.contains(r.getClinic().getId()))
                .collect(Collectors.toList());

        Map<String, Double> earningsByWeek = new HashMap<>();
        LocalDate now = LocalDate.now();
        WeekFields weekFields = WeekFields.ISO;
        for (RentRequest req : acceptedRequests) {
            LocalDate date = req.getCreatedAt().atZone(ZoneId.systemDefault()).toLocalDate();
            int weekNum = date.get(weekFields.weekOfWeekBasedYear());
            int year = date.getYear();
            String weekKey = String.format("%d-W%02d", year, weekNum);
            double price = clinicMap.get(req.getClinic().getId()).getPricePerDay();
            earningsByWeek.put(weekKey, earningsByWeek.getOrDefault(weekKey, 0.0) + price);
        }
        List<String> last4Weeks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            LocalDate weekDate = now.minusWeeks(i);
            int weekNum = weekDate.get(weekFields.weekOfWeekBasedYear());
            int year = weekDate.getYear();
            last4Weeks.add(String.format("%d-W%02d", year, weekNum));
        }
        Collections.reverse(last4Weeks);
        List<WeeklyEarningsDTO> result = new ArrayList<>();
        for (String week : last4Weeks) {
            result.add(new WeeklyEarningsDTO(week, earningsByWeek.getOrDefault(week, 0.0)));
        }
        return result;
    }
}
