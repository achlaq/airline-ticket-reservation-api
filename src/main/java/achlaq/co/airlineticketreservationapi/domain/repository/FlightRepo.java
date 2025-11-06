package achlaq.co.airlineticketreservationapi.domain.repository;

import achlaq.co.airlineticketreservationapi.domain.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FlightRepo extends JpaRepository<Flight, UUID> {

    @Query("""
        select f from Flight f
        where f.origin = :origin and f.dest = :dest
          and date(f.depTime) = :depDate
          and f.seatsSold < f.seatsTotal
        order by f.depTime
    """)
    List<Flight> search(@Param("origin") String origin, @Param("dest") String dest, @Param("depDate") LocalDate depDate);

    @Modifying
    @Query("""
        update Flight f set f.seatsSold = f.seatsSold + :pax
        where f.id = :flightId and (f.seatsSold + :pax) <= f.seatsTotal
      """)
    int holdSeats(@Param("flightId") UUID flightId, @Param("pax") int pax);

    @Modifying
    @Query("""
        update Flight f set f.seatsSold = GREATEST(0, f.seatsSold - :pax)
        where f.id = :flightId
    """)
    int releaseSeats(@Param("flightId") UUID flightId, @Param("pax") int pax);
}