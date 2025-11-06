package achlaq.co.airlineticketreservationapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="booking_flight",
        uniqueConstraints=@UniqueConstraint(name="uk_booking_flight",
                columnNames={"booking_id","flight_id"}))
@Getter @Setter
public class BookingFlight extends BaseEntity {

    @ManyToOne(fetch= FetchType.LAZY) @JoinColumn(name="booking_id", nullable=false)
    private Booking booking;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="flight_id", nullable=false)
    private Flight flight;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal priceSnapshot;
}

