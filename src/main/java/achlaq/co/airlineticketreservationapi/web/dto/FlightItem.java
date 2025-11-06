package achlaq.co.airlineticketreservationapi.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record FlightItem(
        UUID id, String flightNo, String origin, String dest,
        LocalDateTime depTime, LocalDateTime arrTime,
        BigDecimal price, int seatsLeft
) {}