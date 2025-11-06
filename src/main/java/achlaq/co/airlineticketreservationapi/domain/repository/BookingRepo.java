package achlaq.co.airlineticketreservationapi.domain.repository;

import achlaq.co.airlineticketreservationapi.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepo extends JpaRepository<Booking, UUID> {

    Optional<Booking> findByPnrCode(String pnr);
}
