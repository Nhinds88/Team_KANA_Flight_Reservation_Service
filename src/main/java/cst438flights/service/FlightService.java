package cst438flights.service;

import cst438flights.domain.Flight;
import cst438flights.domain.FlightInfo;
import cst438flights.domain.FlightRepository;
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
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanout;

    public FlightInfo getFlightInfo(String arrivalairport) {

        List<Flight> flights = flightRepository.findByArrivalairport(arrivalairport);

        if (flights.size() == 0) {
            return null;
        }

        Flight f = flights.get(0);

        return new FlightInfo(f.getFlightid(), f.getDepartureairport(), f.getArrivalairport(), f.getDeparturedate(), f.getStatus());
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

    public void requestReservation(String departureAirport, String arrivalAirport,
                                   String departureDate, String seatClass,
                                   int numPassengers, boolean priorityBoarding,
                                   String origin, int flightID
    ) {
        String msg = "{\"departureDate\": \"" + departureAirport + "\" \"arrivalAirport\": \"" + arrivalAirport + "\" \"departureDate\": \"" + departureDate + "\"}";
        System.out.println("Sending message:" + msg);
        rabbitTemplate.convertSendAndReceive(fanout.getName(), "", msg);
    }
}