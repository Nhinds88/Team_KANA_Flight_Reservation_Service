package cst438flights.service;

import cst438flights.domain.*;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanout;

    public List<Flight> getFlightInfoArrival(String arrivalairport) {

        List<Flight> flights = flightRepository.findByArrivalairport(arrivalairport);

        if (flights.size() == 0) {
            return null;
        }

        return flights;
    }

    public List<Flight> getFlightInfoDepature(String departureairport) {

        List<Flight> flights = flightRepository.findByDepartureairport(departureairport);

        if (flights.size() == 0) {
            return null;
        }

        return flights;
    }

    public List<Flight> getAvailableFights(String departureAirport, String arrivalairport) {
        return flightRepository.findByDepartureArrivalAirport(departureAirport,arrivalairport);
        /*
        if (flights.size() == 0) {
            return null;
        }
        Flight f = flights.get(0);
        return new FlightInfo(f.getFlightid(), f.getDepartureairport(), f.getArrivalairport(), f.getDeparturedate(), f.getStatus());
        */
    }

    public Reservation requestReservation(String email, String seatClass,
                                          int numPassengers, boolean priorityBoarding,
                                          String origin, int flightID
    ) {
        //some sanity checks
        if(email == "" || origin == "" || seatClass == "") return null;
        if(numPassengers < 1) return null;

        //get customer by email
        Customer customer = customerRepository.findByEmail(email);
        //if no customer by that email make a new one
        if(customer == null) {
            //make a new customer
            customer = new Customer(email);
            //save and update that customer
            customer = customerRepository.save(customer);
        }

        String boardingString = (priorityBoarding ? "yes" : "no");
        float perSeat = 0;
        float priorityPrice = 0;
        if (boardingString.equals("yes")) priorityPrice = 100;
        //should probably add seat class as a repository / tie to database
        if(seatClass.equals("economy")) perSeat = 100;
        else if(seatClass.equals("business")) perSeat = 200;
        else if(seatClass.equals("first")) perSeat = 300;
        float totalPrice = (perSeat + priorityPrice) * numPassengers;

        //make the reservation
        Reservation reservation = new Reservation(customer.getCustomerid(), flightID, seatClass, numPassengers, boardingString, totalPrice, origin, "confirmed");
        //save and update the reservation
        reservation = reservationRepository.save(reservation);
        System.out.println(reservation);
        return reservation;
        //return null;
    }
}
