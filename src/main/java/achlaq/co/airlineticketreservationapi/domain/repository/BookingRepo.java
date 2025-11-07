package achlaq.co.airlineticketreservationapi.domain.repository;

import achlaq.co.airlineticketreservationapi.domain.entity.Booking;
import achlaq.co.airlineticketreservationapi.web.dto.BookingListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepo extends JpaRepository<Booking, UUID>, JpaSpecificationExecutor<Booking> {


    Optional<Booking> findByPnrCode(String pnr);
}
