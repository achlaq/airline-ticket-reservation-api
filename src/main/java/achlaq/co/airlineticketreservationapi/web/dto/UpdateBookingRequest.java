package achlaq.co.airlineticketreservationapi.web.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UpdateBookingRequest(
        String contactName, String contactEmail, String contactPhone,
        List<PassengerPatch> passengers
){
    public record PassengerPatch(
            UUID passengerId, String fullName, String paxType,
            String docNo, LocalDate birthDate
    ) {}
}
