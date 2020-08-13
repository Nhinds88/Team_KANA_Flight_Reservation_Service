package cst438flights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cst438flights.domain.Customer;
import cst438flights.domain.FlightInfo;
import cst438flights.domain.ReservationFlightInfo;
import cst438flights.domain.ReservationRepository;
import cst438flights.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
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
    private JacksonTester<List<ReservationFlightInfo>> reservationJson;
    private JacksonTester<List<FlightInfo>> flightInfoJson;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void getCustomerFlightInfo() throws Exception {
        Timestamp timestamp = new Timestamp(999999999);
        FlightInfo flightInfo1 = new FlightInfo(
                1, "departureAirport1", "arrivalAirport1",
                timestamp, 10, 20, 30, "on time"
        );
        FlightInfo flightInfo2 = new FlightInfo(
                2, "departureAirport2", "arrivalAirport2",
                timestamp, 5, 10, 15, "canceled"
        );
        List<FlightInfo> flightList = new ArrayList<>();
        flightList.add(flightInfo1);
        flightList.add(flightInfo2);
        given(customerService.getPreviousFlights("test_email")).willReturn(flightList);

        MockHttpServletResponse res = mvc.perform(get("/api/previous_flights/test_email"))
                .andReturn().getResponse();

        assertThat(res.getStatus()).isEqualTo(HttpStatus.OK.value());

        FlightInfo expectedFlightInfo1 = new FlightInfo(
                1, "departureAirport1", "arrivalAirport1",
                timestamp, 10, 20, 30, "on time"
        );
        FlightInfo expectedFlightInfo2 = new FlightInfo(
                2, "departureAirport2", "arrivalAirport2",
                timestamp, 5, 10, 15, "canceled"
        );
        List<FlightInfo> expectedFlightList = new ArrayList<>();
        expectedFlightList.add(expectedFlightInfo1);
        expectedFlightList.add(expectedFlightInfo2);

        ObjectContent<List<FlightInfo>> result = flightInfoJson.parse(res.getContentAsString());

    }

    @Test
    public void getCustomerReservationInfo() throws Exception{
        Timestamp timestamp = new Timestamp(999999999);
        ReservationFlightInfo reservationFlightInfo1 = new ReservationFlightInfo(
                1, 2, "departureAirport1", "arrivalAirport1",
                timestamp, "on time", "confirmed", "kana"
        );
        ReservationFlightInfo reservationFlightInfo2 = new ReservationFlightInfo(
                2, 3, "departureAirport2", "arrivalAirport2",
                timestamp, "delayed", "cancelled", "planner"
        );
        List<ReservationFlightInfo> reservationList = new ArrayList<>();
        reservationList.add(reservationFlightInfo1);
        reservationList.add(reservationFlightInfo2);
        given(customerService.getPreviousReservationsRest("test_email")).willReturn(reservationList);

        MockHttpServletResponse res = mvc.perform(get("/api/previous_reservation/test_email"))
                .andReturn().getResponse();

        assertThat(res.getStatus()).isEqualTo(HttpStatus.OK.value());

        ReservationFlightInfo expectedReservationFlightInfo1 = new ReservationFlightInfo(
                1, 2, "departureAirport1", "arrivalAirport1",
                timestamp, "on time", "confirmed", "kana"
        );
        ReservationFlightInfo expectedReservationFlightInfo2 = new ReservationFlightInfo(
                2, 3, "departureAirport2", "arrivalAirport2",
                timestamp, "delayed", "cancelled", "planner"
        );
        List<ReservationFlightInfo> expectedReservationList = new ArrayList<>();
        expectedReservationList.add(expectedReservationFlightInfo1);
        expectedReservationList.add(expectedReservationFlightInfo2);

        ObjectContent<List<ReservationFlightInfo>> result = reservationJson.parse(res.getContentAsString());

        assertThat(result).isEqualTo(expectedReservationList);
    }
}
