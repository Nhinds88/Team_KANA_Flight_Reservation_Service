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
}