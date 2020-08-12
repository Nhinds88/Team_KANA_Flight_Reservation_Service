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

    // not working assert fails
    @Test
    public void getPrevFlightTest() {

        FlightInfo flightInfo = new FlightInfo(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");
        List<FlightInfo> flightInfoList = new ArrayList<FlightInfo>();
        flightInfoList.add(flightInfo);

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "kana", "confirmed");
        List<Reservation> reservationList = new ArrayList<Reservation>();
        reservationList.add(reservation);

        System.out.println(reservation.toString());

        given(customerRepository.findByEmail("jedi@lightside.com")).willReturn(customer);
        given(reservationRepository.findByCustomerid(0)).willReturn(reservationList);
        given(flightRepository.findByFlightid(0)).willReturn(flight);

        List<FlightInfo> resultFlightInfo = customerService.getPreviousFlights("jedi@lightside.com");

        assertThat(resultFlightInfo).isEqualTo(flightInfoList);
    }

    // create test for getPreviousReservation

}