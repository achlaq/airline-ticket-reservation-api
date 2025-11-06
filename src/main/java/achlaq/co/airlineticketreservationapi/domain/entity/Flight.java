package achlaq.co.airlineticketreservationapi.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="flight")
@Getter @Setter
public class Flight extends BaseEntity{

    @Column(nullable=false, length=10)
    private String flightNo;

    @Column(nullable=false, length=3)
    private String origin;

    @Column(nullable=false, length=3)
    private String dest;

    @Column(nullable=false)
    private LocalDateTime depTime;

    @Column(nullable=false)
    private LocalDateTime arrTime;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal price;

    @Column(nullable=false)
    private int seatsTotal;

    @Column(nullable=false)
    private int seatsSold;

}

