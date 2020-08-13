package cst438flights.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cst438flights.domain.Customer;
import cst438flights.domain.Flight;
import cst438flights.domain.Reservation;
import cst438flights.service.FlightService;
import org.junit.Before;
import org.junit.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightRestController.class)
public class FlightRestControllerTest {

    @MockBean
    private FlightService flightService;

    @Autowired
    private MockMvc mvc;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<Flight> json;

    private JacksonTester<Reservation> reservationJson;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void contextLoads() { }

    @Test
    public void getFlightInfoArrivalTest() throws Exception {

        Flight f1 = new Flight(0, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        Flight f2 = new Flight(101, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");

        List<Flight> flight = new ArrayList<Flight>();
        flight.add(f1);
        flight.add(f2);
        // give flight
        given(flightService.getFlightInfoArrival("test_arrival_airport")).willReturn((flight));
        // response from mvc
        MockHttpServletResponse response = mvc.perform(get("/api/flights/arrival/test_arrival_airport")).andReturn().getResponse();

        String responseString = mvc.perform(get("/api/flights/arrival/test_arrival_airport")).andReturn().getResponse().getContentAsString();
        // check the status
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ObjectMapper mapper = new ObjectMapper();
        List<Flight> returnFlight = mapper.readValue(responseString, new TypeReference<List<Flight>>() {});

        Flight expected_f1 = new Flight(0, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        Flight expected_f2 = new Flight(101, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        List<Flight> expectedFlight = new ArrayList<Flight>();
        expectedFlight.add(expected_f1);
        expectedFlight.add(expected_f2);
        // final assert test
        assertThat(returnFlight.toString()).isEqualTo(expectedFlight.toString());
    }

    @Test
    public void getFlightInfoDepartureTest() throws Exception {

        Flight f1 = new Flight(0, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        Flight f2 = new Flight(101, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");

        List<Flight> flight = new ArrayList<Flight>();
        flight.add(f1);
        flight.add(f2);
        // give flight
        given(flightService.getFlightInfoDeparture("test_departure_airport")).willReturn((flight));
        // response from mvc
        MockHttpServletResponse response = mvc.perform(get("/api/flights/departure/test_departure_airport")).andReturn().getResponse();

        String responseString = mvc.perform(get("/api/flights/departure/test_departure_airport")).andReturn().getResponse().getContentAsString();
        // check the status
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ObjectMapper mapper = new ObjectMapper();
        List<Flight> returnFlight = mapper.readValue(responseString, new TypeReference<List<Flight>>() {});

        Flight expected_f1 = new Flight(0, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        Flight expected_f2 = new Flight(101, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        List<Flight> expectedFlight = new ArrayList<Flight>();
        expectedFlight.add(expected_f1);
        expectedFlight.add(expected_f2);
        // final assert test
        assertThat(returnFlight.toString()).isEqualTo(expectedFlight.toString());
    }

    @Test
    public void getAvailableFlightsTest() throws Exception {

        Flight f1 = new Flight(0, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        Flight f2 = new Flight(101, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");

        List<Flight> flight = new ArrayList<Flight>();
        flight.add(f1);
        flight.add(f2);
        // give flight
        given(flightService.getAvailableFights("test_departure_airport", "test_arrival_airport")).willReturn((flight));
        // response from mvc
        MockHttpServletResponse response = mvc.perform(get("/api/flights/test_departure_airport/test_arrival_airport")).andReturn().getResponse();

        String responseString = mvc.perform(get("/api/flights/test_departure_airport/test_arrival_airport")).andReturn().getResponse().getContentAsString();
        // check the status
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ObjectMapper mapper = new ObjectMapper();
        List<Flight> returnFlight = mapper.readValue(responseString, new TypeReference<List<Flight>>() {});

        Flight expected_f1 = new Flight(0, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        Flight expected_f2 = new Flight(101, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
        List<Flight> expectedFlight = new ArrayList<Flight>();
        expectedFlight.add(expected_f1);
        expectedFlight.add(expected_f2);
        // final assert test
        assertThat(returnFlight.toString()).isEqualTo(expectedFlight.toString());
    }

    @Test
    public void createReservationFlightTest() throws Exception { getAvailableFlightsTest(); }

    // not working, getting 404 error
    @Test
    public void createReservationTest() throws Exception {

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "kana", "confirmed");

        given(flightService.requestReservation(customer.getEmail(), "first", 1, false, "kana", flight.getFlightid())).willReturn(reservation);

        // response from mvc
        MockHttpServletResponse response = mvc.perform(post("/api/flight/reservation/jedi@lightside.com/first/1/false/0")).andReturn().getResponse();

        System.out.println("Response Status " + response.getStatus());

        String responseString = mvc.perform(post("/api/flight/reservation/jedi@lightside.com/first/1/false/0")).andReturn().getResponse().getContentAsString();
        // check the status
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ObjectMapper mapper = new ObjectMapper();
        List<Reservation> resultReservation = mapper.readValue(responseString, new TypeReference<List<Reservation>>() {});

        List<Reservation> expectedReservation = new ArrayList<>();
        Reservation expectedRes = new Reservation(0, 0, "first", 1, "no", 300f, "kana", "confirmed");
        expectedReservation.add(expectedRes);

        assertThat(resultReservation).isEqualTo(expectedReservation);
    }


    // not working, getting 404 error
    @Test
    public void createReservation2Test() throws Exception {

        Flight flight = new Flight(0, "departure", "arrival", new Timestamp(2323223232L), 0, 0, 0, "on time");

        Customer customer = new Customer(0, "Skywalker", "Luke", "jedi@lightside.com", "force");

        Reservation reservation = new Reservation(0, 0, "first", 1, "no", 300f, "kana", "confirmed");

        given(flightService.requestReservation(customer.getEmail(), "first", 1, false, "kana", flight.getFlightid())).willReturn(reservation);

        // response from mvc
        MockHttpServletResponse response = mvc.perform(get("/api/flight/reservation2/jedi@lightside.com/first/1/false/0")).andReturn().getResponse();

        System.out.println("Response Status " + response.getStatus());

        String responseString = mvc.perform(get("/api/flight/reservation2/jedi@lightside.com/first/1/false/0")).andReturn().getResponse().getContentAsString();
        // check the status
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ObjectMapper mapper = new ObjectMapper();
        List<Reservation> resultReservation = mapper.readValue(responseString, new TypeReference<List<Reservation>>() {});

        List<Reservation> expectedReservation = new ArrayList<>();
        Reservation expectedRes = new Reservation(0, 0, "first", 1, "no", 300f, "kana", "confirmed");
        expectedReservation.add(expectedRes);

        assertThat(resultReservation).isEqualTo(expectedReservation);
    }
}