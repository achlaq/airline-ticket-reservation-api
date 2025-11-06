package achlaq.co.airlineticketreservationapi.domain.repository;

import achlaq.co.airlineticketreservationapi.domain.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PassengerRepo extends JpaRepository<Passenger, UUID> {

    long countByBooking_Id(UUID bookingId);
}