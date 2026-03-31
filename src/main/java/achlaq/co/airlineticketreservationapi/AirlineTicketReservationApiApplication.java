package achlaq.co.airlineticketreservationapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Airline Ticket Reservation API", version = "1.0", description = "API for managing airline ticket reservations"))
public class AirlineTicketReservationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineTicketReservationApiApplication.class, args);
    }

}
