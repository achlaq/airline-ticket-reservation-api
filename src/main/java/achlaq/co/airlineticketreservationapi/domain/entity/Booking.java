package achlaq.co.airlineticketreservationapi.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="booking")
@Getter @Setter
public class Booking extends BaseEntity{

    @Column(name="pnr_code", unique=true, nullable=false, length=8)
    private String pnrCode;

    @Column(nullable=false, length=12)
    private String status;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal totalAmount;


}
