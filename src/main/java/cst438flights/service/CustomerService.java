package cst438flights.service;

import cst438flights.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightInfo> getPreviousFlights(String email) {

        System.out.println("Email " + email);

        Customer customer = customerRepository.findByEmail(email);

        List<Reservation> reservations = reservationRepository.findByCustomerid(customer.getCustomerid());

        System.out.println("reservation list size " + reservations.size());

        List<FlightInfo> flights = new ArrayList<FlightInfo>();

        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            Flight previousFlight = flightRepository.findByFlightid(r.getDepartureflightid());
            System.out.println("Flight Departure Airport (Flight) " +  previousFlight.getDepartureairport());
            FlightInfo tempFlight = new FlightInfo(previousFlight.getFlightid(), previousFlight.getDepartureairport(), previousFlight.getArrivalairport(), previousFlight.getDeparturedate());
            flights.add(tempFlight);
            System.out.println("Flight Departure Airport (FlightInfo) " + tempFlight.getDepartureAirport());
        }

        return flights;
    }
}