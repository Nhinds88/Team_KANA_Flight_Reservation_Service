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

    public FlightInfo getFlightInfo(String arrivalAirport) {

        List<Flight> flights = flightRepository.findByArrivalAirport(arrivalAirport);

        if (flights.size() == 0) {
            return null;
        }

        Flight f = flights.get(0);

        return new FlightInfo(f.getFlight_ID(), f.getDepartureAirport(), f.getArrivalAirport(), f.getDepartureDate());
    }

    public void requestReservation(String departureAirport, String arrivalAirport, String departureDate) {
        String msg = "{\"departureDate\": \"" + departureAirport + "\" \"arrivalAirport\": \"" + arrivalAirport + "\" \"departureDate\": \"" + departureDate + "\"}";
        System.out.println("Sending message:" + msg);
        rabbitTemplate.convertSendAndReceive(fanout.getName(), "", msg);
    }
}