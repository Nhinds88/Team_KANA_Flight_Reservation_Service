package cst438flights.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

//    @Test
//    public void getFlightInfoArrival() throws Exception {
//
//        Flight f1 = new Flight(0, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
//        Flight f2 = new Flight(101, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
//
//        List<Flight> flight = new ArrayList<Flight>();
//        flight.add(f1);
//        flight.add(f2);
//        // give flight
//        given(flightService.getFlightInfoArrival("test_arrival_airport")).willReturn((flight));
//        // response from mvc
//        MockHttpServletResponse response = mvc.perform(get("/api/flights/arrival/test_arrival_airport")).andReturn().getResponse();
//
//        String responseString = mvc.perform(get("/api/flights/arrival/test_arrival_airport")).andReturn().getResponse().getContentAsString();
//        // check the status
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        // convert json to an object
////        Flight returnedF = json.parseObject(response.getContentAsString());
////        List<Flight> returnFlight = new ArrayList<>();
////        returnFlight.add(returnedF);
////        List<Flight> returnFlight = json.parseObject(response.getContentAsString());
//        ObjectMapper mapper = new ObjectMapper();
//        List<Flight> returnFlight = mapper.readValue(responseString, new TypeReference<List<Flight>>() {});
//
//        Flight expected_f1 = new Flight(0, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
//        Flight expected_f2 = new Flight(101, "test_departure_airport", "test_arrival_airport", new Timestamp(2323223232L), 1, 1, 1, "on time");
//        List<Flight> expectedFlight = new ArrayList<Flight>();
//        expectedFlight.add(expected_f1);
//        expectedFlight.add(expected_f2);
//        // final assert test
//        assertThat(returnFlight).isEqualTo(expectedFlight);
//    }

//    @Test
//    public void createReservationTest() throws Exception {
//
//        Reservation reservation = new Reservation(
//                9999,9999,9999,
//                "first", 1, "no",
//                300f, "kana", "confirmed");
//
//        given(flightService.requestReservation("test_email", "first", 1, false, "kana", 9999)).willReturn(reservation);
//
//        MockHttpServletResponse response = mvc.perform(get("/api/flight/reservation")).andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

//        Reservation resultReservation = reservationJson.parseObject(response.getContentAsString());
//
//        assertThat(reservation).isEqualTo(resultReservation);
//        Reservation reservation = new Reservation(
//                9999,9999,9999,
//                "first", 1, "no",
//                300f, "kana", "confirmed");
//
//        ObjectMapper mapper = new ObjectMapper();
////        String jsonRes = mapper.writeValueAsString(reservation);
//        String jsonRes = "";
//
//        try {
//            jsonRes = mapper.writeValueAsString(reservation);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        given(flightService.requestReservation("test_email", "first", 1, false, "kana", 9999)).willReturn(reservation);
//
//        MockHttpServletResponse response = null;
//
//        try {
//            response = mvc.perform(get("/api/flight/reservation").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonRes)).andReturn().getResponse();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//    }
}