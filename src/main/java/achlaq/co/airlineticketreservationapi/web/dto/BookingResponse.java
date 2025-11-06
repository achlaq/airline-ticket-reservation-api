package achlaq.co.airlineticketreservationapi.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BookingResponse(
        String pnr, String status, BigDecimal totalAmount,
        UUID bookingId, UUID flightId
) {}
