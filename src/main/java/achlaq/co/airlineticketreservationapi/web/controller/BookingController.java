package achlaq.co.airlineticketreservationapi.web.controller;

import achlaq.co.airlineticketreservationapi.service.BookingService;
import achlaq.co.airlineticketreservationapi.web.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public BookingResponse detail(@PathVariable UUID id){
        return service.get(id);
    }

    @GetMapping("/search")
    public Page<BookingListItem> list(
            @RequestParam(required = false) String pnr,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return service.search(pnr, status, from, to, page, size);
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

