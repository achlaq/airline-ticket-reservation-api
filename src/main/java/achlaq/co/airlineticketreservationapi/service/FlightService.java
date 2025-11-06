package achlaq.co.airlineticketreservationapi.service;

import achlaq.co.airlineticketreservationapi.domain.entity.Flight;
import achlaq.co.airlineticketreservationapi.domain.repository.FlightRepo;
import achlaq.co.airlineticketreservationapi.web.dto.FlightItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepo repo;

    public List<FlightItem> search(String origin, String dest, LocalDate date){
        return repo.search(origin, dest, date).stream().map(f ->
                new FlightItem(f.getId(), f.getFlightNo(), f.getOrigin(), f.getDest(),
                        f.getDepTime(), f.getArrTime(), f.getPrice(), f.getSeatsTotal()-f.getSeatsSold())
        ).toList();
    }

    public FlightItem get(UUID id){
        Flight f = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        return new FlightItem(f.getId(), f.getFlightNo(), f.getOrigin(), f.getDest(),
                f.getDepTime(), f.getArrTime(), f.getPrice(), f.getSeatsTotal()-f.getSeatsSold());
    }
}
