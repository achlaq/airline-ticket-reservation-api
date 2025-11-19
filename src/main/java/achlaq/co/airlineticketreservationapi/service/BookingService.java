package achlaq.co.airlineticketreservationapi.service;

import achlaq.co.airlineticketreservationapi.domain.entity.Booking;
import achlaq.co.airlineticketreservationapi.domain.entity.BookingFlight;
import achlaq.co.airlineticketreservationapi.domain.entity.Flight;
import achlaq.co.airlineticketreservationapi.domain.entity.Passenger;
import achlaq.co.airlineticketreservationapi.domain.repository.BookingFlightRepo;
import achlaq.co.airlineticketreservationapi.domain.repository.BookingRepo;
import achlaq.co.airlineticketreservationapi.domain.repository.FlightRepo;
import achlaq.co.airlineticketreservationapi.domain.repository.PassengerRepo;
import achlaq.co.airlineticketreservationapi.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final FlightRepo flightRepo;
    private final BookingRepo bookingRepo;
    private final BookingFlightRepo bookingFlightRepo;
    private final PassengerRepo passengerRepo;
    private final PnrService pnr;

    @Transactional
    public BookingResponse create(CreateBookingRequest req){
        Flight fl = flightRepo.findById(req.flightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        int pax = req.passengers().size();

        if (flightRepo.holdSeats(fl.getId(), pax) != 1)
            throw new IllegalStateException("Seat not available");

        Booking bk = new Booking();
        bk.setPnrCode(pnr.generateUnique(p -> bookingRepo.findByPnrCode(p).isPresent(), 6));
        bk.setStatus("HOLD");
        bk.setContactName(req.contactName());
        bk.setContactEmail(req.contactEmail());
        bk.setContactPhone(req.contactPhone());
        bk.setTotalAmount(fl.getPrice().multiply(BigDecimal.valueOf(pax)));
        bk.setCreatedDate(LocalDateTime.now());
        bk.setUpdatedDate(LocalDateTime.now());
        bookingRepo.save(bk);

        BookingFlight bf = new BookingFlight();
        bf.setBooking(bk);
        bf.setFlight(fl);
        bf.setPriceSnapshot(fl.getPrice());
        bookingFlightRepo.save(bf);

        for (var pi : req.passengers()){
            Passenger ps = new Passenger();
            ps.setBooking(bk);
            ps.setFullName(pi.fullName());
            ps.setPaxType(pi.paxType());
            ps.setBirthDate(pi.birthDate());
            ps.setDocNo(pi.docNo());
            passengerRepo.save(ps);
        }
        return new BookingResponse(bk.getPnrCode(), bk.getStatus(), bk.getTotalAmount(), bk.getId(), fl.getId());
    }

    public List<BookingItem> search(
            String pnr, String status,
            LocalDate from, LocalDate to
    ) {
        final LocalDateTime fromDt = (from == null) ? null : from.atStartOfDay();
        final LocalDateTime toDt   = (to   == null) ? null : to.plusDays(1).atStartOfDay();

        return bookingRepo.getBookings(normalizeString(pnr), normalizeString(status), fromDt, toDt)
                .stream().map(b -> new BookingItem(
                        b.getPnrCode(),
                        b.getStatus(),
                        b.getContactName(),
                        b.getContactEmail(),
                        b.getContactPhone(),
                        b.getTotalAmount(),
                        b.getCreatedDate()
                )).toList();
    }

    public BookingResponse get(UUID id){
        Booking b = bookingRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        UUID flightId = bookingFlightRepo.findByBooking_Id(b.getId()).get(0).getFlight().getId();
        return new BookingResponse(b.getPnrCode(), b.getStatus(), b.getTotalAmount(), b.getId(), flightId);
    }

    @Transactional
    public BookingResponse update(String pnrCode, UpdateBookingRequest req){
        Booking b = bookingRepo.findByPnrCode(pnrCode)
                .orElseThrow(() -> new IllegalArgumentException("PNR not found"));
        if (req.contactName()!=null)  b.setContactName(req.contactName());
        if (req.contactEmail()!=null) b.setContactEmail(req.contactEmail());
        if (req.contactPhone()!=null) b.setContactPhone(req.contactPhone());

        if (req.passengers()!=null){
            for (var p : req.passengers()){
                if (p.passengerId()==null) continue;
                Passenger entity = passengerRepo.findById(p.passengerId())
                        .orElseThrow(() -> new IllegalArgumentException("Passenger not found: "+p.passengerId()));
                if (!entity.getBooking().getId().equals(b.getId()))
                    throw new IllegalStateException("Passenger not in this booking");
                if (p.fullName()!=null) entity.setFullName(p.fullName());
                if (p.paxType()!=null)  entity.setPaxType(p.paxType());
                if (p.docNo()!=null)    entity.setDocNo(p.docNo());
                if (p.birthDate()!=null)entity.setBirthDate(p.birthDate());
            }
        }
        b.setUpdatedDate(LocalDateTime.now());
        bookingRepo.save(b);

        UUID flightId = bookingFlightRepo.findByBooking_Id(b.getId()).get(0).getFlight().getId();
        return new BookingResponse(b.getPnrCode(), b.getStatus(), b.getTotalAmount(), b.getId(), flightId);
    }

    @Transactional
    public void cancel(String pnrCode){
        Booking b = bookingRepo.findByPnrCode(pnrCode)
                .orElseThrow(() -> new IllegalArgumentException("PNR not found"));
        if ("CANCELLED".equals(b.getStatus())) return;

        int pax = (int) passengerRepo.countByBooking_Id(b.getId());
        UUID flightId = bookingFlightRepo.findByBooking_Id(b.getId()).get(0).getFlight().getId();
        flightRepo.releaseSeats(flightId, pax);

        b.setStatus("CANCELLED");
        b.setUpdatedDate(LocalDateTime.now());
        bookingRepo.save(b);
    }

    private String normalizeString(String s) {
        if (s == null || s.isBlank()) return null;
        return s.trim();
    }
}

