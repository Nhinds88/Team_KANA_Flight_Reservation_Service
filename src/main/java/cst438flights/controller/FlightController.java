package cst438flights.controller;

import cst438flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlightController {
    //for holding the origin name
    static final String ORIGIN = "kana";

    @Autowired
    private FlightService flightService;

    @PostMapping("/flight/reservation")
    public String createReservation(
            @RequestParam("bookEmail") String email,
            @RequestParam("departureAirport") String departureAirport,
            @RequestParam("arrivalAirport") String arrivalAirport,
            @RequestParam("departureDate") String departureDate,
            @RequestParam("seatClass") String seatClass,
            @RequestParam("numPassengers") int numPassengers,
            @RequestParam("flightID") int flightID,
            @RequestParam(value = "prioBoarding", defaultValue = "false") boolean prioBoarding,
            Model model) {

        model.addAttribute("departureAirport", departureAirport);
        model.addAttribute("arrivalAirport", arrivalAirport);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("seatClass", seatClass);
        model.addAttribute("numPassengers", numPassengers);
        model.addAttribute("prioBoarding", prioBoarding);

        flightService.requestReservation(email, seatClass, numPassengers, prioBoarding, ORIGIN, flightID);

        return "request_reservation";
    }
}