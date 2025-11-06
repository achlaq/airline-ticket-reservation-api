package achlaq.co.airlineticketreservationapi.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="passenger", indexes=@Index(name="idx_passenger_booking",
        columnList="booking_id"))
@Getter @Setter
public class Passenger extends BaseEntity{

    @ManyToOne(fetch= FetchType.LAZY) @JoinColumn(name="booking_id", nullable=false)
    private Booking booking;

    @Column(nullable=false, length=80)
    private String fullName;

    @Column(nullable=false, length=4)
    private String paxType;

    private LocalDate birthDate;

    private String docNo;
}

