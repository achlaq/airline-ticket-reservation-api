package achlaq.co.airlineticketreservationapi.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record SearchFlightRequest(
        @Pattern(regexp="^[A-Z]{3}$") String origin,
        @Pattern(regexp="^[A-Z]{3}$") String dest,
        @NotNull LocalDate date
) {}