package achlaq.co.airlineticketreservationapi.domain.repository;

import achlaq.co.airlineticketreservationapi.domain.entity.BookingFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingFlightRepo extends JpaRepository<BookingFlight, UUID> {

    List<BookingFlight> findByBooking_Id(UUID bookingId);
}