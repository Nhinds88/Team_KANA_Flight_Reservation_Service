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
        List<Customer> customer = customerRepository.findByEmail(email);

        if (customer.size() == 0) {
            return null;
        }

        Customer c = customer.get(0);

        List<Reservation> reservations = reservationRepository.findByCustomerID(c.getCustomer_ID());

        if (reservations.size() == 0) {
            return null;
        }

        List<FlightInfo> flights = new ArrayList<FlightInfo>();

        for (int i = 0; i >= reservations.size(); i++) {
            Reservation r = reservations.get(i);
            Flight previousFlight = flightRepository.findByFlightID(r.getDepartureFlight_ID());
            flights.add(new FlightInfo(previousFlight.getFlightID(), previousFlight.getDepartureAirport(), previousFlight.getArrivalAirport(), previousFlight.getDepartureDate()));
        }

        return flights;
    }
}