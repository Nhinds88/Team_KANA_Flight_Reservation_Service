package cst438flights.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cst438flights.domain.*;
import cst438flights.service.CustomerService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {

    @MockBean
    private CustomerService customerService;

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private MockMvc mvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Customer> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void contextLoads() { }

    @Test
    public void getCustomerPreviousFlightsTest() throws Exception {

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        FlightInfo flightInfo = new FlightInfo(flight);
        List<FlightInfo> flightInfoList = new ArrayList<>();
        flightInfoList.add(flightInfo);

        given(customerService.getPreviousFlights("jedi@lightside.com")).willReturn(flightInfoList);

        String email = "jedi@lightside.com";

        MockHttpServletResponse response = mvc.perform(get("/api/previous_flights/email")).andReturn().getResponse();

        String responseString = mvc.perform(get("/api/previous_flights/jedi@lightside.com")).andReturn().getResponse().getContentAsString();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ObjectMapper mapper = new ObjectMapper();
        List<FlightInfo> resultFightInfo = mapper.readValue(responseString, new TypeReference<List<FlightInfo>>() {});

        FlightInfo expectedFI = new FlightInfo(flight);
        List<FlightInfo> expectedFlightInfo = new ArrayList<>();
        expectedFlightInfo.add(expectedFI);

        assertThat(resultFightInfo.toString()).isEqualTo(expectedFlightInfo.toString());
    }

    @Test
    public void getCustomerPreviousReservationsRestTest() throws Exception {

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "kana", "confirmed");

        List<ReservationFlightInfo> reservationFlightInfoList = new ArrayList<>();
        reservationFlightInfoList.add(new ReservationFlightInfo(reservation.getReservationid(), flight.getFlightid(), flight.getDepartureairport(),
                flight.getArrivalairport(), flight.getDeparturedate(), flight.getStatus(), reservation.getBookingStatus(), reservation.getReservationorigin()));

        given(customerService.getPreviousReservationsRest("jedi@lightside.com")).willReturn(reservationFlightInfoList);

        MockHttpServletResponse response = mvc.perform(get("/api/previous_reservation/email")).andReturn().getResponse();

        String responseString = mvc.perform(get("/api/previous_reservation/email")).andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<ReservationFlightInfo> resultReservationFlightInfo = mapper.readValue(responseString, new TypeReference<List<ReservationFlightInfo>>() {});

        List<ReservationFlightInfo> expectedReservationFlightInfo = new ArrayList<>();
        expectedReservationFlightInfo.add(new ReservationFlightInfo(reservation.getReservationid(), flight.getFlightid(), flight.getDepartureairport(),
                flight.getArrivalairport(), flight.getDeparturedate(), flight.getStatus(), reservation.getBookingStatus(), reservation.getReservationorigin()));

        assertThat(reservationFlightInfoList.toString()).isEqualTo(expectedReservationFlightInfo.toString());
    }

    @Test
    public void cancelReservationRestTest() throws Exception{

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "Notkana", "confirmed");

        given(reservationRepository.findByReservationid(0)).willReturn(reservation);

        MockHttpServletResponse response = mvc.perform(get("/api/cancel_reservation/0")).andReturn().getResponse();

        String responseString = mvc.perform(get("/api/cancel_reservation/0")).andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        Reservation resultReservation = mapper.readValue(responseString, new TypeReference<Reservation>() {});

        Reservation expectedReservation = new Reservation(0, 0, "first", 1, "no", 300f, "Notkana", "confirmed");

        assertThat(resultReservation.toString()).isEqualTo(expectedReservation.toString());
    }
}