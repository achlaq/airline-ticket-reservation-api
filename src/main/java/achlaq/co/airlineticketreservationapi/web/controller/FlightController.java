package achlaq.co.airlineticketreservationapi.web.controller;

import achlaq.co.airlineticketreservationapi.service.FlightService;
import achlaq.co.airlineticketreservationapi.web.dto.FlightItem;
import achlaq.co.airlineticketreservationapi.web.dto.SearchFlightRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService service;

    @GetMapping("/search")
    public List<FlightItem> search(@Valid SearchFlightRequest q){
        return service.search(q.origin(), q.dest(), q.date());
    }

    @GetMapping("/{id}")
    public FlightItem detail(@PathVariable UUID id){
        return service.get(id);
    }
}

