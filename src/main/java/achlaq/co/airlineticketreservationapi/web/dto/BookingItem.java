package achlaq.co.airlineticketreservationapi.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingItem(
        String pnr,
        String status,
        String contactName,
        String contactEmail,
        String contactPhone,
        BigDecimal totalAmount,
        LocalDateTime createdDate
) {}