package achlaq.co.airlineticketreservationapi.web.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CreateBookingRequest(
        @NotNull UUID flightId,
        @NotBlank String contactName,
        @Email String contactEmail,
        @NotBlank String contactPhone,
        @NotEmpty List<PassengerItem> passengers
){
    public record PassengerItem(
            @NotBlank String fullName,
            @Pattern(regexp="^(ADT|CHD|INF)$") String paxType,
            LocalDate birthDate, String docNo
    ) {}
}