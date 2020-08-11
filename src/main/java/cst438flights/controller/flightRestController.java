package cst438flights.controller;

import cst438flights.domain.Flight;
import cst438flights.domain.FlightInfo;
import cst438flights.domain.Reservation;
import cst438flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class flightRestController {
    //for holding the origin name
    static final String ORIGIN = "planner";

    @Autowired
    private FlightService flightService;

    @GetMapping("/api/flights/{flight_ID}")
    public ResponseEntity<FlightInfo> getFlight(String arrivalAirport) {

        FlightInfo flightInfo = flightService.getFlightInfo(arrivalAirport);

        if (flightInfo == null) {
            return new ResponseEntity<FlightInfo>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<FlightInfo>(flightInfo, HttpStatus.OK);
        }
    }

    @GetMapping("/api/flights/{departureAirport}/{arrivalAirport}")
    public ResponseEntity<List<Flight>> getAvailableFlights(
            @PathVariable("departureAirport") String departureAirport,
            @PathVariable("arrivalAirport") String arrivalAirport
            ) {

        List<Flight> flightInfo = flightService.getAvailableFights(departureAirport, arrivalAirport);
        System.out.println(flightInfo);

        if (flightInfo == null) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Flight>>(flightInfo, HttpStatus.OK);
        }
    }

    @PostMapping("/api/flight/reservation")
    public ResponseEntity<Reservation> createReservation(
            @RequestParam("email") String email,
            @RequestParam("seatClass") String seatClass,
            @RequestParam("numPassengers") int numPassengers,
            @RequestParam("prioBoarding") boolean prioBoarding,
            @RequestParam("flightID") int flightID
    ) {

        Reservation reservation = flightService.requestReservation(email, seatClass, numPassengers, prioBoarding, ORIGIN, flightID);

        if (reservation == null) {
            return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
        }
    }
    
    //getMapping option to book reservation
        @GetMapping("/api/flight/reservation2/{email}/{seatClass}/{numPassengers}/{prioBoarding}/{flightID}")
    public ResponseEntity<Reservation> createReservation2(
    		@PathVariable("email") String email,
    		@PathVariable("seatClass") String seatClass,
    	    @PathVariable("numPassengers") int numPassengers,
    	    @PathVariable("prioBoarding") boolean prioBoarding,
            @PathVariable("flightID") int flightID
    ) {
        Reservation reservation = flightService.requestReservation(email, seatClass, numPassengers, prioBoarding, ORIGIN, flightID);
        if (reservation == null) {
            return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
        }
    }
}
