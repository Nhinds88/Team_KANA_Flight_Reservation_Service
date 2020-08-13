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
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void contextLoads() { }

    @Test
    public void getPrevFlightTest() {

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "kana", "confirmed");
        List<Reservation> reservationList = new ArrayList<Reservation>();
        reservationList.add(reservation);

        given(customerRepository.findByEmail("jedi@lightside.com")).willReturn(customer);
        given(reservationRepository.findByCustomerid(0)).willReturn(reservationList);
        given(flightRepository.findByFlightid(0)).willReturn(flight);

        List<FlightInfo> resultFlightInfo = customerService.getPreviousFlights("jedi@lightside.com");
        List<FlightInfo> expectedFlightInfo = new ArrayList<>();
        expectedFlightInfo.add(new FlightInfo(flight.getFlightid(), flight.getDepartureairport(), flight.getArrivalairport(), flight.getDeparturedate(), flight.getFirstclass(), flight.getBusinessclass(), flight.getEconomyclass(), flight.getStatus()));

        assertThat(resultFlightInfo.toString()).isEqualTo(expectedFlightInfo.toString());
    }

    @Test
    public void getPrevReservationTest() {

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "kana", "confirmed");
        List<Reservation> reservationList = new ArrayList<Reservation>();
        reservationList.add(reservation);

        given(customerRepository.findByEmail("jedi@lightside.com")).willReturn(customer);
        given(reservationRepository.findByCustomerid(0)).willReturn(reservationList);
        given(flightRepository.findByFlightid(0)).willReturn(flight);

        List<ReservationFlightInfo> resultReservationFlightInfo = customerService.getPreviousReservations("jedi@lightside.com");
        List<ReservationFlightInfo> expectedReservationFlightInfo = new ArrayList<>();
        expectedReservationFlightInfo.add(new ReservationFlightInfo(reservation.getReservationid(), flight.getFlightid(), flight.getDepartureairport(),
                flight.getArrivalairport(), flight.getDeparturedate(), flight.getStatus(), reservation.getBookingStatus(), reservation.getReservationorigin()));

        assertThat(resultReservationFlightInfo.toString()).isEqualTo(expectedReservationFlightInfo.toString());
    }

    @Test
    public void getPrevReservationRestTest() {

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "planner", "confirmed");
        List<Reservation> reservationList = new ArrayList<Reservation>();
        reservationList.add(reservation);

        given(customerRepository.findByEmail("jedi@lightside.com")).willReturn(customer);
        given(reservationRepository.findByCustomerid(0)).willReturn(reservationList);
        given(flightRepository.findByFlightid(0)).willReturn(flight);

        List<ReservationFlightInfo> resultReservationFlightInfo = customerService.getPreviousReservationsRest("jedi@lightside.com");
        List<ReservationFlightInfo> expectedReservationFlightInfo = new ArrayList<>();
        expectedReservationFlightInfo.add(new ReservationFlightInfo(reservation.getReservationid(), flight.getFlightid(), flight.getDepartureairport(),
                flight.getArrivalairport(), flight.getDeparturedate(), flight.getStatus(), reservation.getBookingStatus(), reservation.getReservationorigin()));

        assertThat(resultReservationFlightInfo).isEqualTo(expectedReservationFlightInfo);
    }

    @Test
    public void updateStatusTest() {

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 5, 5, 5, "on time");

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "planner", "confirmed");
        List<Reservation> reservationList = new ArrayList<Reservation>();
        reservationList.add(reservation);

        //flightRepository.save(flight);
        //reservationRepository.save(reservation);

        given(customerRepository.findByEmail("jedi@lightside.com")).willReturn(customer);
        given(reservationRepository.findByCustomerid(0)).willReturn(reservationList);
        given(reservationRepository.findByReservationid(0)).willReturn(reservation);
        given(flightRepository.findByFlightid(0)).willReturn(flight);

        customerService.updateStatus("0");

        Flight resultFlight = flightRepository.findByFlightid(0);
        Reservation resultReservation = reservationRepository.findByReservationid(0);

        Flight expectedFlight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 6, 5, 5, "on time");
        Reservation expectedReservation = new Reservation(0, 0, "first", 1, "no", 300f, "planner", "cancelled");

        assertThat(resultFlight).isEqualTo(expectedFlight);
        assertThat(resultReservation).isEqualTo(expectedReservation);
    }

    @Test
    public void updateStatusRestTest() {

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 5, 5, 5, "on time");

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "planner", "confirmed");
        List<Reservation> reservationList = new ArrayList<Reservation>();
        reservationList.add(reservation);

        //flightRepository.save(flight);
        //reservationRepository.save(reservation);

        given(customerRepository.findByEmail("jedi@lightside.com")).willReturn(customer);
        given(reservationRepository.findByCustomerid(0)).willReturn(reservationList);
        given(reservationRepository.findByReservationid(0)).willReturn(reservation);
        given(flightRepository.findByFlightid(0)).willReturn(flight);

        customerService.updateStatusRest("0");

        Flight resultFlight = flightRepository.findByFlightid(0);
        Reservation resultReservation = reservationRepository.findByReservationid(0);

        Flight expectedFlight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 6, 5, 5, "on time");
        Reservation expectedReservation = new Reservation(0, 0, "first", 1, "no", 300f, "planner", "cancelled");

        assertThat(resultFlight).isEqualTo(expectedFlight);
        assertThat(resultReservation).isEqualTo(expectedReservation);
    }
}