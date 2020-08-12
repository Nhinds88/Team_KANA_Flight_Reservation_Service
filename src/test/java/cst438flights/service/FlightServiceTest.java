package cst438flights.service;

import cst438flights.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class FlightServiceTest {

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private FlightService flightService;

    @Test
    public void contextLoads() { }

    @Test
    public void arrivalFlightFound() {

        Flight flight = new Flight(0, "departureAirport", "arrivalAirport", new Timestamp(2323223232L), 0, 0, 0, "on time");

        List<Flight> flightList = new ArrayList<Flight>();
        flightList.add(flight);

        given(flightRepository.findByArrivalairport("arrivalAirport")).willReturn(flightList);

        List<Flight> resultFlightList = flightService.getFlightInfoArrival("arrivalAirport");

        assertThat(resultFlightList).isEqualTo(flightList);
    }

    @Test
    public void arrivalFlightNotFound() {

        Flight flight = new Flight(0, "departureAirport", "arrivalAirport", new Timestamp(2323223232L), 0, 0, 0, "on time");

        List<Flight> flightList = new ArrayList<Flight>();
        flightList.add(flight);

        given(flightRepository.findByArrivalairport("arrivalAirport")).willReturn(flightList);

        List<Flight> resultFlightList = flightService.getFlightInfoArrival("arrivalAirportNOT");
        List<Flight> expectedFlightList = null;

        assertThat(resultFlightList).isEqualTo(expectedFlightList);
    }

    @Test
    public void DepartureFlightFound() {

        Flight flight = new Flight(0, "departureAirport", "arrivalAirport", new Timestamp(2323223232L), 0, 0, 0, "on time");

        List<Flight> flightList = new ArrayList<Flight>();
        flightList.add(flight);

        given(flightRepository.findByDepartureairport("departureAirport")).willReturn(flightList);

        List<Flight> resultFlightList = flightService.getFlightInfoDeparture("departureAirport");

        assertThat(resultFlightList).isEqualTo(flightList);
    }

    @Test
    public void departureFlightNotFound() {

        Flight flight = new Flight(0, "departureAirport", "arrivalAirport", new Timestamp(2323223232L), 0, 0, 0, "on time");

        List<Flight> flightList = new ArrayList<Flight>();
        flightList.add(flight);

        given(flightRepository.findByDepartureairport("departureAirport")).willReturn(flightList);

        List<Flight> resultFlightList = flightService.getFlightInfoDeparture("departureAirportNOT");
        List<Flight> expectedFlightList = null;

        assertThat(resultFlightList).isEqualTo(expectedFlightList);
    }

    @Test
    public void availableFlightTest() {
        Flight flight1 = new Flight(0, "departureAirport", "arrivalAirport", new Timestamp(2323223232L), 0, 0, 0, "on time");
        Flight flight2 = new Flight(1, "departureAirport", "arrivalAirport", new Timestamp(2323223232L), 0, 0, 0, "on time");
        Flight flight3 = new Flight(2, "departureAirport", "arrivalAirport", new Timestamp(2323223232L), 0, 0, 0, "on time");

        List<Flight> flightList = new ArrayList<Flight>();
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);

        given(flightRepository.findByDepartureArrivalAirport("departureAirport", "arrivalAirport")).willReturn(flightList);

        List<Flight> resultFlightList = flightService.getAvailableFights("departureAirport", "arrivalAirport");

        assertThat(resultFlightList).isEqualTo(flightList);
    }
}