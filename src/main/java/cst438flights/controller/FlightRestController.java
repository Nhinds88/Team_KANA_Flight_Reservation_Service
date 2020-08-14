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
public class FlightRestController {
    //for holding the origin name
    static final String ORIGIN = "planner";

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

        List<Flight> departureFlights = flightService.getFlightInfoDeparture(departureAirport);

        if (departureFlights == null) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Flight>>(departureFlights, HttpStatus.OK);
        }
    }

    @GetMapping("/api/flights/{departureAirport}/{arrivalAirport}")
    public ResponseEntity<List<Flight>> getAvailableFlights(
            @PathVariable("departureAirport") String departureAirport,
            @PathVariable("arrivalAirport") String arrivalAirport,
            String date
    ) {
        if(date == null) {
            date = "";
        }

        List<Flight> flightInfo = flightService.getAvailableFights(departureAirport, arrivalAirport, date);
        System.out.println(flightInfo);

        if (flightInfo == null) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Flight>>(flightInfo, HttpStatus.OK);
        }
    }

    @GetMapping("/api/flights2/{departureAirport}/{arrivalAirport}/{month}/{day}/{year}")
    public ResponseEntity<List<Flight>> getAvailableFlights(
            @PathVariable("departureAirport") String departureAirport,
            @PathVariable("arrivalAirport") String arrivalAirport,
            @PathVariable("month") String month,
            @PathVariable("day") String day,
            @PathVariable("year") String year
    ) {

        String date = month +"/"+ day+"/"+ year;

        System.out.println("assembled date" + date);

        List<Flight> flightInfo = flightService.getAvailableFights(departureAirport, arrivalAirport, date);
        System.out.println(flightInfo);

        if (flightInfo == null) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Flight>>(flightInfo, HttpStatus.OK);
        }
    }

    @PostMapping("/api/flights")
    public ResponseEntity<List<Flight>> getAvailableFlightsPost(
            @RequestParam("departureAirport") String departureAirport,
            @RequestParam("arrivalAirport") String arrivalAirport,
            @RequestParam("date") String date
    ) {

        List<Flight> flightInfo = flightService.getAvailableFights(departureAirport, arrivalAirport, date);
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
