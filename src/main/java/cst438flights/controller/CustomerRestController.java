package cst438flights.controller;

import cst438flights.domain.FlightInfo;
import cst438flights.domain.Reservation;
import cst438flights.domain.ReservationFlightInfo;
import cst438flights.domain.ReservationRepository;
import cst438flights.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("api/previous_flights/{email}")
    public ResponseEntity<List<FlightInfo>> getCustomerPreviousFlights(
            @PathVariable("email") String email) {

        List<FlightInfo> previousFlights = customerService.getPreviousFlights(email);

        System.out.println(previousFlights.get(0));

        if (previousFlights == null) {
            return new ResponseEntity<List<FlightInfo>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<FlightInfo>>(previousFlights, HttpStatus.OK);
        }
    }

    @GetMapping("api/previous_reservation/{email}")
    public ResponseEntity<List<ReservationFlightInfo>> getCustomerPreviousReservationsRest(
            @PathVariable("email") String email) {

        List<ReservationFlightInfo> previousReservation = customerService.getPreviousReservationsRest(email);

        System.out.println(previousReservation.get(0));

        if (previousReservation == null) {
            return new ResponseEntity<List<ReservationFlightInfo>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<ReservationFlightInfo>>(previousReservation, HttpStatus.OK);
        }
    }

    @GetMapping("api/cancel_reservation/{reservationID}")
    public ResponseEntity<Reservation> cancelReservationRest(
            @PathVariable("reservationID") Integer reservationID) {

        String res_ID_string = Integer.toString(reservationID);
        customerService.updateStatusRest(res_ID_string); // Cancels the reservation
        Reservation reservation = reservationRepository.findByReservationid(reservationID);

        System.out.println(reservation);

        //prevents non-planner/non-existent flight cancellations
        if (reservation == null || (reservation.getReservationorigin()).equals("kana")) {
            return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
        }
    }
}