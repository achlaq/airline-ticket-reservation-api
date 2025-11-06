package achlaq.co.airlineticketreservationapi.web.controller;

import achlaq.co.airlineticketreservationapi.service.BookingService;
import achlaq.co.airlineticketreservationapi.web.dto.BookingResponse;
import achlaq.co.airlineticketreservationapi.web.dto.CreateBookingRequest;
import achlaq.co.airlineticketreservationapi.web.dto.UpdateBookingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse create(@Valid @RequestBody CreateBookingRequest req){
        return service.create(req);
    }

    @PatchMapping("/{pnr}")
    public BookingResponse update(@PathVariable String pnr, @RequestBody UpdateBookingRequest req){
        return service.update(pnr, req);
    }

    @PostMapping("/{pnr}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String pnr){
        service.cancel(pnr);
    }
}

