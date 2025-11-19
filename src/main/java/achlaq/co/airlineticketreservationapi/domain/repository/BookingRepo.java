package achlaq.co.airlineticketreservationapi.domain.repository;

import achlaq.co.airlineticketreservationapi.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepo extends JpaRepository<Booking, UUID>, JpaSpecificationExecutor<Booking> {

    @Query("""
        SELECT b
        FROM Booking b
        WHERE (:pnr IS NULL OR b.pnrCode = :pnr)
            AND (:status IS NULL OR b.status = :status)
            AND (b.createdDate >= COALESCE(:fromDate, b.createdDate))
            AND (b.createdDate <= COALESCE(:toDate,   b.createdDate))
        ORDER BY b.createdDate DESC
    """)
    List<Booking> getBookings (String pnr, String status,
                               LocalDateTime fromDate, LocalDateTime toDate);

    Optional<Booking> findByPnrCode(String pnr);
}
