package cst438flights.controller;

import cst438flights.domain.Flight;
import cst438flights.domain.FlightInfo;
import cst438flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class flightRestController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/api/flights/arrival/{arrivalAirport}")
    public ResponseEntity<List<Flight>> getFlightArrival(
            @PathVariable("arrivalAirport") String arrivalAirport) {

         List<Flight> arrivalFlights = flightService.getFlightInfoArrival(arrivalAirport);

        if (arrivalFlights == null) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Flight>>(arrivalFlights, HttpStatus.OK);
        }
    }

    @GetMapping("/api/flights/departure/{departureAirport}")
    public ResponseEntity<List<Flight>> getFlightDeparture(
            @PathVariable("departureAirport") String departureAirport) {

        List<Flight> depatureFlights = flightService.getFlightInfoDepature(departureAirport);

        if (depatureFlights == null) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Flight>>(depatureFlights, HttpStatus.OK);
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
}